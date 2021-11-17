from api_invoke.BaseInvoker import BaseInvoker


class SelectInvoker(BaseInvoker):
    def __init__(self):
        super().__init__()

    def get_user_info_list(self):
        for i in range(0, self.page):
            response = self.get_response(path="/User/get_all", param={"page": i, "page_size": self.batch_size})
            flag = self.response_parser(response)
            if not flag:
                break


if __name__ == '__main__':
    s_i = SelectInvoker()
    s_i.get_user_info_list()
