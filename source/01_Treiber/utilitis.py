import requests


def readXml(apiUrl, apiName):
    xml = False
    try:
        resp = requests.get(apiUrl + "/" + apiName)  # 'http://localhost:8080/api/alarms'
        if resp.status_code != 200:
            # This means something went wrong.
            raise ApiError('GET /tasks/ {}'.format(resp.status_code))
        xml = resp.content
    except:
        print "tomcat nicht gestartet??"
        xml = False

    return xml
