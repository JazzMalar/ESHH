from c_api import callApi

import sys
sys.path.append('../')
from configparser import readConfig
from c_LEDStrip2 import LEDStrip
from c_deviceActions import deviceActions

####Singleton !!
class deviceWS2801:
    class __strip:
        def __init__(self):
            configParam = "api-config"
            config = readConfig("../Config/treiberConfig.txt", configParam)
            apiURL = config.getConfigParam("api")
            self.ws2801 = callApi(apiURL, "devices?StringID=WS2801_01").getArray()[0]
            configParam = "treiber-config"
            ledConfig = readConfig("../Config/treiberConfig.txt", configParam)
            #self.deviceActions = callApi(apiURL,"deviceactions?StringID="+self.ws2801['stringId']+"&ID="+self.ws2801['deviceId']).getArray()[0]
            self.deviceActions = deviceActions(apiURL,"deviceactions?StringID="+self.ws2801['stringId']+"&ID="+self.ws2801['deviceId'])
            #self.strip = LEDStrip(ledConfig.getConfigParam("anzleds"), ledConfig.getConfigParam("striptype"),ledConfig.getConfigParam("debugmode"))
            self.strip = LEDStrip(self.deviceActions.getFieldValue("NumLeds"), self.ws2801['stringId'],
                                  ledConfig.getConfigParam("debugmode"))
        def getStrip(self):
            return self.strip

    instance = None
    def __init__(self):
        if not deviceWS2801.instance:
            deviceWS2801.instance = deviceWS2801.__strip()


    def getStrip(self):
        return deviceWS2801.instance.getStrip()




# dev = deviceWS2801()
# strip1 = deviceWS2801().getStrip()
# #
# dev2 = deviceWS2801()
# strip2 = dev2.getStrip()
#
# print str(strip1)+" , "+str(strip2)

