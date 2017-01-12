# coding: utf8
from c_LED2 import LED
from c_logger import logger
from c_LedHandler import LEDHandler
import sys
import socket
import time




class LEDStrip(object):

    #WS2801
    #LPD6803
    #debug = true --> test ohne RASPI, spi-funktionen werden ausgeklammert
    def __init__(self,anzahlLEDs, stripType="WS2801",debug=False):
        self.logger = logger("LEDStrip")
        self.__anzLeds     = int(anzahlLEDs)
        self.__strip = []
        self.__setStripType(stripType)
        self.__setDebug(debug)


        self.logger.log("debug","create LEDStrip mit "+str(anzahlLEDs)+"LEDs")

        for led in range(0, self.__anzLeds):
            self.__strip.append(LEDHandler())
        self.logger.log("debug","Strip:")
        self.logger.log("debug",self.__strip)

    def getFullStrip(self):
        self.logger.log("debug","getFullStrip()")
        return self.__strip

    def setFullStripColor(self,r,g,b,prio,usedBy=1):
        for i in self.__strip:
            i.setRGB(r,g,b,prio,usedBy)

    def setOneLEDColor(self,ledNumber,r,g,b,prio,usedBy=1):
        if ledNumber < self.__anzLeds:
            self.__strip[ledNumber].setRGB(r,g,b,prio,usedBy)

    def getLEDNumber(self,ledNumber):
        self.logger.log("debug","getLEDNumber ledNumber: "+str(ledNumber))
        if ledNumber > (self.__anzLeds -1):
            return -1
        else:
            return self.__strip[ledNumber]

    def removeLEDUsedByFullStrip(self,usedBy):
        found = 0
        for i in self.__strip:
            if (i.removeLEDUsedBy(usedBy)):
                found = found + 1
        return found

    def removeLEDUsedBy(self, ledNumber,usedBy):
        return self.__strip[ledNumber].removeLEDUsedBy(usedBy)



    def printStrip(self):
        for i in range(0, self.__anzLeds):
            led = self.getLEDNumber(i)
            print "["+str(i)+"] RGB "+str(hex(led.getR()))+"/"+str(hex(led.getG()))+"/"+str(hex(led.getB()))+" Prio: "+str(led.getPrio())+" usedBy: "+str(led.getUsedBy())

    def printStripChanges(self):
        for i in range(0, self.__anzLeds):
            led = self.getLEDNumber(i)
            if led.isNewColor():
                print "[" + str(i) + "] RGB " + str(hex(led.getR())) + "/" + str(hex(led.getG())) + "/" + str(
                hex(led.getB())) + " Prio: " + str(led.getPrio()) + " usedBy: " + str(led.getUsedBy())

    def __setStripType(self,type):
        if "ws2801" in type.lower():
            self.stripType = "WS2801"
        elif "lpd6803" in type.lower():
            self.stripType = "LPD6803"
        else:
            self.stripType = "WS2801"

    def __getStripType(self):
        return self.stripType

    def __setDebug(self,onOff):
        if onOff.lower() == "false":
            self.debug= False
        elif onOff.lower() == "true":
            self.debug=True

    def __getDebug(self):
        return self.debug

    def setColorToStrip(self):
        #print "setColorToStrip()"
        # 3 bytes per pixel
        PIXEL_SIZE = 3
        PIXEL_SIZE_SM16716 = 4
        pixel_output = bytearray(self.__anzLeds * PIXEL_SIZE + 3);
        # print "update"
        # self.getFullColor()
        # pixel_output = self.list[:]

        for pixel_offset in [(x * 3) for x in range(0, self.__anzLeds)]:
            stripPixel = pixel_offset / 3
            # print "stripPixel: "+str(stripPixel)
            if self.__getStripType() == "LPD6803":
                pixel_output[pixel_offset:] = self.__strip[stripPixel].getRGB_LPD6803()
            else:
                pixel_output[pixel_offset:] = self.__strip[stripPixel].getRGB()
        self.__writeStream(pixel_output)


    def __writeStream(self, pixels):
        # 3 bytes per pixel
        #print "__writeStream getDebug:"+str(self.__getDebug())
        PIXEL_SIZE = 3
        PIXEL_SIZE_SM16716 = 4
        if not self.__getDebug():
            spi_dev_name = '/dev/spidev0.0';
            spidev = file(spi_dev_name, "wb")

        if self.__getStripType() == "LPD6803":
            pixel_out_bytes = bytearray(2)
            if not self.__getDebug():
                spidev.write(bytearray(4))
            pixel_count = len(pixels) / PIXEL_SIZE
            for pixel_index in range(pixel_count):
                pixel_in = bytearray(pixels[(pixel_index * PIXEL_SIZE):((pixel_index * PIXEL_SIZE) + PIXEL_SIZE)])

                pixel_out = 0b1000000000000000  # bit 16 must be ON
                pixel_out |= (pixel_in[0] & 0x00F8) << 7  # RED is bits 11-15
                pixel_out |= (pixel_in[1] & 0x00F8) << 2  # GREEN is bits 6-10
                pixel_out |= (pixel_in[2] & 0x00F8) >> 3  # BLUE is bits 1-5

                pixel_out_bytes[0] = (pixel_out & 0xFF00) >> 8
                pixel_out_bytes[1] = (pixel_out & 0x00FF) >> 0
                if not self.__getDebug():
                    spidev.write(pixel_out_bytes)
            if not self.__getDebug():
                spidev.write(bytearray(len(pixels) / 8 + 1))
        elif self.__getStripType() == "LPD8806":
            if not self.__getDebug():
                spidev.write(pixels)
                spidev.write(bytearray(b'\x00\x00\x00'))  # zero fill the last to prevent stray colors at the end
                spidev.write(bytearray(b'\x00'))
        elif self.__getStripType() == "SM16716":
            # Each frame for SM17616 starts with 50bits set to '0'
            # Also every pixel needs to start with a bit set to '1'
            if not self.__getDebug():
                spidev.write(bytearray(
                b'\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00') + pixels)
        else:
            if not self.__getDebug():

                spidev.write(pixels)
        if not self.__getDebug():
            spidev.flush()
        return