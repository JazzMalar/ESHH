from c_LED2 import LED
from c_logger import logger

class LEDObject(LED):
    def __init__(self):
        self.logger = logger("LEDObject")
        self.logger.log("debug","init LEDObject")
        self.__prio = 0
        LED.__init__(self)
        self.__usedBy = 0

    def getPrio(self):
        return self.__prio

    def getUsedBy(self):
        return self.__usedBy

    def setRGB(self, r, g, b, prio, usedBy):
        self.__usedBy = usedBy
        self.__prio = prio
        LED.setRGB(self,r,g,b)