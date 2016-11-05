from c_LEDStrip2 import LEDStrip

strip = LEDStrip(10,"WS2801",True)
strip.getFullStrip()
strip.setFullStripColor(233,233,1,1)
strip.setOneLEDColor(5,1,1,233,2)

strip.setColorToStrip()
strip.printStrip()