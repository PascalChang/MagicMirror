import RPi.GPIO as gpio
import picamera
import time
import pyimgur
from imgurpython import ImgurClient
import requests


CLIENT_ID = "83cc24fa5dc0f71"
client_secret = 'f22e56662bfeea8c6987129bbf9049e3170b9355'  #Imgur Clinet Secret
access_token = '8be958ad9f9618b8e93f989941b6b3eade0d4109'   #透過postman獲取的token
refresh_token = '49f85db530b9f2b69c6b7a9aa6efa1af4cee44fa'  #透過postman獲取的token
msg = '有異狀'
token = 'ncZHzs2RbdLA3ndLkPq1dqd3ESbJumuf4LpJVj5fBW3'

gpio.setmode(gpio.BOARD)
gpio.setup(26, gpio.IN, pull_up_down=gpio.PUD_UP)
#gpio.setup(11, gpio.OUT)
def action(channel):
    print("Motion detected")
    camera.capture("picture/who.jpeg")
    print("Take a Picture")
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
#    for i in range(10):
#        gpio.output(11, True)
#        time.sleep(0.2)
#        gpio.output(11, False)
#        time.sleep(0.2)
    lineNotifyMessage(token, msg, image)

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
    return r.status_code





camera=picamera.PiCamera()
camera.resolution=(1024,768)
camera.brightness=60

try:

    gpio.add_event_detect(26, gpio.RISING, callback=action, bouncetime=200)
    
    while True:
        time.sleep(1)
except:
    camera.close()
    gpio.cleanup()