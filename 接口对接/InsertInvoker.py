import sys
import os

rootPath = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
# print(rootPath)
sys.path.append(rootPath)

from api_invoke.BaseInvoker import BaseInvoker
from zhihu_user_info_spider.requester.SearchRequester import SearchRequester
from data_analysis.util.SaveUtil import SaveUtil
from data_analysis.analysis.DataAnalysis import DataAnalysis
import time

search_requester = SearchRequester()
save_util = SaveUtil()
# data_analysis = DataAnalysis()


class InsertInvoker(BaseInvoker):
    def __init__(self):
        super().__init__()
        self.queue_file = os.getcwd() + os.sep + "queue.txt"
        self.data_dict = {}
        self.json_list = []

    def __spider_user_info(self, uuid):
        user_json = search_requester.get_user_info(uuid)
        return user_json

    def insert_user_into_search_table(self):
        while (True):
            flag = os.path.exists(self.queue_file)
            if flag:
                f = open(self.queue_file, mode="r", encoding="utf-8")
                uuid = f.readline()
                json_result = self.__spider_user_info(uuid)
                self.json_list.append(json_result)
                self.data_dict["data"] = self.json_list
                '''这里等待立军的搜索表的添加接口'''
                response = self.post_response("/User_check/insert", data=self.data_dict)
                if not self.response_parser(response):
                    while (True):
                        response = self.post_response("/User_check/insert", data=self.data_dict)
                        if self.response_parser(response):
                            break
                        else:
                            time.sleep(1)
                f.close()
                os.remove(self.queue_file)
            else:
                time.sleep(1)
                print("沉睡一次")

    # def insert_user_into_user_table(self):
    #     base_df = data_analysis.base_low_df
    #     base_df.columns = ["id2", "url_token", "name", "gender", "level", "avatar_url", "type",
    #                        "headline", "description", "business", "following_count", "follower_count", "answer_count",
    #                        "zvideo_count",
    #                        "question_count", "articles_count", "columns_count", "favorite_count",
    #                        "following_question_count",
    #                        "following_topic_count", "following_columns_count", "following_favlists_count", "location",
    #                        "voteup_count"]
    #     # print(base_df.to_dict(orient="records"))
    #     user_list = base_df.to_dict(orient="records")
    #     for dict_item in user_list:
    #         for j in dict_item:
    #             if j == "location":
    #                 set_list = list(set(dict_item[j].split("'")))
    #                 if set_list.__contains__(", "):
    #                     set_list.remove(", ")
    #                 if set_list.__contains__("]"):
    #                     set_list.remove("]")
    #                 if set_list.__contains__("["):
    #                     set_list.remove("[")
    #                 if set_list.__contains__("[]"):
    #                     set_list.remove("[]")
    #                 dict_item[j] = set_list
    #         # print(dict_item)
    #     self.data_dict["data"] = user_list
    #     response = self.post_response("/User/insert", data=self.data_dict)
    #     if self.response_parser(response):
    #         print(response)
    #     else:
    #         print("error")
    #

if __name__ == '__main__':
    insert_invoke = InsertInvoker()
    insert_invoke.insert_user_into_search_table()
    # insert_invoke.insert_user_into_user_table()
