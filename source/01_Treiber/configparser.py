import ConfigParser

class readConfig:
    def __init__(self,file,configName):
        self.file       = file
        self.configName = configName
        self.config     = ""

        self.configParser = ConfigParser.RawConfigParser()
        self.configFilePath = r''+self.file
        self.__readConfig()

    def __readConfig(self):
        self.configParser.read(self.configFilePath)
        self.config = dict(self.configParser.items(self.configName))

    def printAllConfig(self):
        print self.config

    def getConfigParam(self,param):
        return self.config[param]

# configParser = ConfigParser.RawConfigParser()
# configFilePath = r'treiberConfig.txt'
# configParser.read(configFilePath)
#
# #anzLeds = configParser.get('treiber-config','anzLEDs')
# test = dict(configParser.items('treiber-config'))
# print test


config = readConfig("Config/treiberConfig.txt","treiber-config")
config.printAllConfig()
print config.getConfigParam("striptype")
