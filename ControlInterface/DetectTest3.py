import RPi.GPIO as gpio
import time
import pyimgur
from imgurpython import ImgurClient
import requests
import cv2
import numpy
from imutils.video import VideoStream


CLIENT_ID = "83cc24fa5dc0f71"
client_secret = 'f22e56662bfeea8c6987129bbf9049e3170b9355'  #Imgur Clinet Secret
access_token = '8be958ad9f9618b8e93f989941b6b3eade0d4109'   #透過postman獲取的token(Imgur)
refresh_token = '49f85db530b9f2b69c6b7a9aa6efa1af4cee44fa'  #透過postman獲取的token(Imgur)
msg = '有異狀'
token = 'ncZHzs2RbdLA3ndLkPq1dqd3ESbJumuf4LpJVj5fBW3'       #LineNotify token

gpio.setmode(gpio.BOARD)
gpio.setup(32, gpio.IN, pull_up_down=gpio.PUD_UP)
flag = 0    #用來結束程式
def action(channel):
   
    
    print("Motion detected")
    vs = VideoStream(usePiCamera=True).start()    #使用RaspiCamerad
    #vs = VideoStream(src=0).start()                #使用WebCame 
    time.sleep(2.0)
    
    while True:
        # grab the frame from the threaded video stream
        frame = vs.read()
    
        gray_img = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        haar_face_cascade = cv2.CascadeClassifier('/usr/share/opencv/haarcascades/haarcascade_frontalface_default.xml')
        faces = haar_face_cascade.detectMultiScale(gray_img, 1.1, 5);
    
    
        for (x, y, w, h) in faces:
            cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
            
        if isinstance(faces,numpy.ndarray):
            
            cv2.imwrite('picture/who.jpeg', frame)
            print("Take a Picture")
			vs.stop()
            vs.stream.release()
            #cv2.destroyAllWindows()
            
            break
            
    
    
    PATH = r"picture/who.jpeg"
    im = pyimgur.Imgur(CLIENT_ID)   
    uploaded_image = im.upload_image(PATH, title="Uploaded with PyImgur")
    trans = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    client = ImgurClient(CLIENT_ID, client_secret, access_token, refresh_token)
    album = 'vIGukN5' # 相簿ID
    config = {      #上傳圖檔的config
            'album': album,
            'name':'capture',
            'title': trans
            }
    image = client.upload_from_url(uploaded_image.link, config=config, anon=False)
    lineNotifyMessage(token, msg, image)        #執行image上傳

def lineNotifyMessage(token, msg, image):
    headers = {
        "Authorization": "Bearer " + token, 
        "Content-Type" : "application/x-www-form-urlencoded"
    }

    payload = {
            'message': msg,
            'imageThumbnail':image['link'],
            'imageFullsize':image['link']
            }
    r = requests.post("https://notify-api.line.me/api/notify", headers = headers, params = payload)
    time.sleep(1)
    print('Notify Send.')
    global flag
    flag+=1
    return r.status_code







try:
    time.sleep(11)
    gpio.add_event_detect(32, gpio.RISING, callback=action, bouncetime=200)
    while True:
        time.sleep(1)
        if(flag==1):
            gpio.cleanup()
            break
            
        
except:
    
    gpio.cleanup()