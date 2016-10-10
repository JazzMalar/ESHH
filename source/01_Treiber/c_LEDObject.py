from c_LED2 import LED
from c_logger import logger

class LEDObject(LED):
    def __init__(self):
        self.logger = logger("LEDObject")
        self.logger.log("debug","init LEDObject")
        self.prio = 0
        LED.__init__(self)
        self.usedBy = 0

    def getPrio(self):
        return self.prio

    def getUsedBy(self):
        return self.usedBy

    def setRGB(self, r, g, b, prio, usedBy):
        self.usedBy = usedBy
        self.prio = prio
        LED.setRGB(self,r,g,b)