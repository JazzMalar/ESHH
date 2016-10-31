# coding: utf8
from c_LED2 import LED
from c_logger import logger
from c_LedHandler import LEDHandler
import sys
import socket
import time




class LEDStrip(object):

    #WS2801
    #LPD8806
    #LPD6803
    #SM16716
    def __init__(self,anzahlLEDs, stripType="WS2801"):
        self.logger = logger("LEDStrip")
        self.__leds     = anzahlLEDs
        self.__strip = []
        self.stripType =stripType


        self.logger.log("debug","create LEDStrip mit "+str(anzahlLEDs)+"LEDs")

        for led in range(0, anzahlLEDs):
            self.__strip.append(LEDHandler())
        self.logger.log("debug","Strip:")
        self.logger.log("debug",self.__strip)

    def getFullStrip(self):
        self.logger.log("getFullStrip()")
        return self.__strip

    def getLEDNumber(self,ledNumber):
        self.logger.log("debug","getLEDNumber ledNumber: "+str(ledNumber))
        if ledNumber > (len(self.__strip) -1):
            return -1
        else:
            return self.__strip[ledNumber]

    def printStrip(self):
        for i in range(0, len(self.__strip)-1):
            led = self.getLEDNumber(i)
            print "["+str(i)+"] RGB "+str(hex(led.getR()))+"/"+str(hex(led.getG()))+"/"+str(hex(led.getB()))+" Prio: "+str(led.getPrio())+" usedBy: "+str(led.getUsedBy())




    # def write_stream(pixels):
    #     # 3 bytes per pixel
    #     PIXEL_SIZE = 3
    #     PIXEL_SIZE_SM16716 = 4
    #
    #     if self.stripType == "LPD6803":
    #         pixel_out_bytes = bytearray(2)
    #         spidev.write(bytearray(4))
    #         pixel_count = len(pixels) / PIXEL_SIZE
    #         for pixel_index in range(pixel_count):
    #             pixel_in = bytearray(pixels[(pixel_index * PIXEL_SIZE):((pixel_index * PIXEL_SIZE) + PIXEL_SIZE)])
    #
    #             pixel_out = 0b1000000000000000  # bit 16 must be ON
    #             pixel_out |= (pixel_in[0] & 0x00F8) << 7  # RED is bits 11-15
    #             pixel_out |= (pixel_in[1] & 0x00F8) << 2  # GREEN is bits 6-10
    #             pixel_out |= (pixel_in[2] & 0x00F8) >> 3  # BLUE is bits 1-5
    #
    #             pixel_out_bytes[0] = (pixel_out & 0xFF00) >> 8
    #             pixel_out_bytes[1] = (pixel_out & 0x00FF) >> 0
    #             spidev.write(pixel_out_bytes)
    #         spidev.write(bytearray(len(pixels) / 8 + 1))
    #     elif self.stripType == "LPD8806":
    #         spidev.write(pixels)
    #         spidev.write(bytearray(b'\x00\x00\x00'))  # zero fill the last to prevent stray colors at the end
    #         spidev.write(bytearray(b'\x00'))
    #     elif self.stripType == "SM16716":
    #         # Each frame for SM17616 starts with 50bits set to '0'
    #         # Also every pixel needs to start with a bit set to '1'
    #         spidev.write(bytearray(
    #             b'\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00') + pixels)
    #     else:
    #         spidev.write(pixels)
    #
    #     return