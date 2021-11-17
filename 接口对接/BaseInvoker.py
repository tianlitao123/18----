from zhihu_user_info_spider.parser.Parser import Parser
import requests
import json
import os
import pandas as pd


class BaseInvoker(object):

    def __init__(self):
        f = open(os.getcwd() + os.sep + "config.json", mode="r", encoding="utf-8")
        self.config = json.load(f)
        self.host = self.config["host"]
        self.port = self.config["port"]
        self.base_url = "http://" + str(self.host) + ":" + str(self.port)
        self.batch_size = self.config["batch_size"]
        self.page = self.config["page"]
        self.post_headers = {
            "Content-Type": "application/json"
        }

    def get_response(self, path: str, param: dict) -> requests.Response:
        url = self.base_url + path + "?"
        for i in param.keys():
            url += i + "=" + str(param[i]) + "&"
        print(url)
        return requests.get(url=url)

    def post_response(self, path: str, data: dict) -> requests.Response:
        url = self.base_url + path
        return requests.post(url, headers=self.post_headers, data=json.dumps(data["data"]))

    def response_parser(self, response: requests.Response):
        json_data = response.json()
        try:
            code = json_data["code"]
        except KeyError:
            print("message：" + json_data["message"] + "\n")
            print("trace：" + json_data["trace"])
            print("未找到code，解析错误，请求返回的数据无法正确解析")
            return False
        if code == 200:
            # data_list = json_data["data"]
            # for i in data_list:
            #     Parser.user_info_parser(i, is_add=False)
            return True
        elif code == 250:
            print("插入成功")
            return True
        elif code == 450:
            print("插入失败")
            return False
        elif code == 400:
            # print(str(json_data["data"]) + "：数据返回为空")
            return False
        else:
            print("响应解析器出错！")


if __name__ == '__main__':
    invoker = BaseInvoker()
    invoker.get_response(path="/User/get_all", param={"page": 0, "page_size": 100})
