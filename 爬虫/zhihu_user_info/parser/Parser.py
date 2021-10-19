from zhihu_user_info.entities.UserEntity import UserEntityList
import parsel
import re

user_entity = UserEntityList()


class Parser(object):
    @staticmethod
    def user_info_parser(json: dict):
        single_user_info_dict = {}
        single_user_info_dict["id"] = json["id"]
        single_user_info_dict["url_token"] = json["url_token"]
        single_user_info_dict["user_name"] = json["name"]
        single_user_info_dict["gender"] = json["gender"]
        single_user_info_dict["level"] = json["level_info"]["level"]
        single_user_info_dict["avatar_url"] = json["avatar_url_template"]
        single_user_info_dict["type"] = json["type"]
        single_user_info_dict["headline"] = json["headline"]
        single_user_info_dict["description"] = json["description"]
        single_user_info_dict["business"] = json["business"]['name']
        single_user_info_dict["following_count"] = json["following_count"]
        single_user_info_dict["follower_count"] = json["follower_count"]
        single_user_info_dict["answer_count"] = json["answer_count"]
        single_user_info_dict["zvideo_count"] = json["zvideo_count"]
        single_user_info_dict["question_count"] = json["question_count"]
        single_user_info_dict["articles_count"] = json["articles_count"]
        single_user_info_dict["columns_count"] = json["columns_count"]
        single_user_info_dict["favorite_count"] = json["favorite_count"]
        single_user_info_dict["following_question_count"] = json["following_question_count"]
        single_user_info_dict["following_topic_count"] = json["following_topic_count"]
        single_user_info_dict["following_columns_count"] = json["following_columns_count"]
        single_user_info_dict["following_favlists_count"] = json["following_favlists_count"]
        location_list = json["location"]
        single_user_info_dict["location"] = []
        for item in location_list:
            single_user_info_dict["location"].append(item["name"])
        single_user_info_dict["voteup_count"] = json["voteup_count"]
        user_entity.add_user(single_user_info_dict)

    @staticmethod
    def hot_question_list_parser(response_text: str):
        if response_text != None and response_text != "":
            url_list = parsel.Selector(response_text).xpath("//div[@class='HotItem-content']/a/@href").getall()
            i = 0
            n = 0
            while i < len(url_list) - n:
                if not url_list[i].__contains__("question"):
                    url_list.pop(i)
                    n = n + 1
                i = i + 1
            if len(url_list) == 0:
                print("接收到的hot文档已失效，请及时更换cookie")
            else:
                id_list = []
                for i in url_list:
                    id_list.append(i.split("/")[-1])
                return id_list
        else:
            print("请输入正确的hot文档")
