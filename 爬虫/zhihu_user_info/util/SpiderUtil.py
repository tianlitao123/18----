import random

# 爬虫工具类主要作用：读取json文件随机选取user_agent
from zhihu_user_info.util.Utils import Util


class SpiderUtil(Util):
    def __init__(self):
        super().__init__()

    # 随机选取用户代理返回
    def get_user_Agent(self):
        if "user_Agent" in self.json_result:
            user_Agent_list = self.json_result["user_Agent"]
            user_Agent = random.choice(user_Agent_list)
            return user_Agent
        else:
            print("请在util_content.json中配置user_Agent。")

    # # 用来获取起始用户信息,这里默认的是我自己找的10个知乎粉丝超10w的大v。(该方案已废弃)
    # def get_base_user_uuid_list(self):
    #     if "base_uuid" in self.json_result:
    #         base_uuid_list = self.json_result["base_uuid"]
    #         return base_uuid_list
    #     else:
    #         print("请在util_content.json中配置base_uuid。")

    # 随机从cookie池中挑选一个cookie返回
    def get_cookie(self):
        if "cookies" in self.json_result:
            cookies_list = self.json_result["cookies"]
            print("cookie池："+str(cookies_list))
            cookie = random.choice(cookies_list)
            return cookie
        else:
            print("请在util_content.json中配置至少一个cookies。")

    # 获取线程数量
    def get_thread_num(self):
        if "thread_num" in self.json_result:
            thread_num = self.json_result["thread_num"]
            return thread_num
        else:
            print("请在util_content.json中配置thread_num。")
