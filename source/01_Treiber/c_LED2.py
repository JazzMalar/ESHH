from c_logger import logger

class LED(object):

    def __init__(self):
        self.logger         = logger("class LED")
        self.red            = 0
        self.green          = 0
        self.blue           = 0
        self.color          = bytearray(b'\x00\x00\x00')
        self.color_LPD6803  = bytearray(b'\x00\x00\x00')
        #Private
        self.__newColor = True
        self.logger.log("debug","init red: "+str(self.red)+ " green: "+str(self.green)+" blue: "+str(self.green))
        return self

    def setR(self,r):
        if(self.red != r):
            self.red            = r
            self.__newColor       = True
        return self

    def setG(self, g):
        if (self.green != g):
            self.green = g
            self.__newColor = True
        return self

    def setB(self, b):
        if (self.blue != b):
            self.blue = b
            self.__newColor = True
        return self

    def setRGB(self,r,g,b):
        self.setR(r)
        self.setG(g)
        self.setB(b)
        return self

    def isNewColor(self):
        temp           = self.__newColor
        self.__newColor       = False
        return temp

    def setNewColorFalse(self):
        self.__newColor = False

    def getR(self):
        return self.red

    def getB(self):
        return self.blue

    def getG(self):
        return self.green

    def getRGB(self):
        self.color[0] = self.red
        self.color[1] = self.green
        self.color[2] = self.blue
        self.setNewColorFalse()
        return self.color


    def getRGB_LPD6803(self):
        self.color_LPD6803[0]   = self.green
        self.color_LPD6803[1]   = self.red
        self.color_LPD6803[2]   = self.blue
        self.setNewColorFalse()
        return self.color_LPD6803