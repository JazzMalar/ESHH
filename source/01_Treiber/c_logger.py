import logging



class logger():
    def __init__(self, name,level=logging.INFO):

        # create logger
        self.logger = logging.getLogger(name)
        self.logger.setLevel(level)

        # create console handler and set level to debug
        self.ch = logging.StreamHandler()
        self.ch.setLevel(level)

        # create formatter
        self.formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        #formatter = logging.Formatter('[%(asctime)s] p%(process)s {%(pathname)s:%(lineno)d} %(levelname)s - %(message)s','%m-%d %H:%M:%S')
        #logging.basicConfig(filename='LED.log', level=logging.DEBUG,format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')

        # add formatter to ch
        self.ch.setFormatter(self.formatter)

        # add ch to logger
        self.logger.addHandler(self.ch)

    def log(self,level,message):
        loglevel = level.lower()
        if loglevel == "critical":
            self.logger.critical(message)
        elif loglevel == "error":
            self.logger.error(message)
        elif loglevel == "warn":
            self.logger.warn(message)
        elif loglevel == "info":
            self.logger.info(message)
        else:
            self.logger.debug(message)


