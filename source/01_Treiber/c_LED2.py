from c_logger import logger

class LED(object):

    def __init__(self):
        self.logger         = logger("class LED")
        self.__red            = 0
        self.__green          = 0
        self.__blue           = 0
        self.__color          = bytearray(b'\x00\x00\x00')
        self.__color_LPD6803  = bytearray(b'\x00\x00\x00')
        self.__newColor = True
        self.logger.log("debug","init red: " + str(self.__red) + " green: " + str(self.__green) + " blue: " + str(self.__green))
        return self

    def setR(self,r):
        if(self.__red != r):
            self.__red            = r
            self.__newColor       = True
        return self

    def setG(self, g):
        if (self.__green != g):
            self.__green = g
            self.__newColor = True
        return self

    def setB(self, b):
        if (self.__blue != b):
            self.__blue = b
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

    def __setNewColorFalse(self):
        self.__newColor = False

    def getR(self):
        return self.__red

    def getB(self):
        return self.__blue

    def getG(self):
        return self.__green

    def getRGB(self):
        self.__color[0] = self.__red
        self.__color[1] = self.__blue
        self.__color[2] = self.__green
        self.__setNewColorFalse()
        return self.__color


    def getRGB_LPD6803(self):
        self.__color_LPD6803[0]   = self.__green
        self.__color_LPD6803[1]   = self.__red
        self.__color_LPD6803[2]   = self.__blue
        self.__setNewColorFalse()
        return self.__color_LPD6803