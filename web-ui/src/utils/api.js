import axios from 'axios';
import { Message } from 'element-ui';
import en from "element-ui/src/locale/lang/en";

axios.interceptors.response.use(success => {
    // http response 200
    if (success.status && success.status == 200 && success.data.status == 500) {
        // 业务处理错误.
        Message.error({
            message: success.data.msg,
        });
        console.log(success);
        return;
    }
    return success.data;
}, error => {
    // http response 400 / 500
    if (error.response.status == 504 || error.response.status == 404) {
        Message.error({
            message: '服务器被吃了, (⊙﹏⊙)∥？！'
        });
    } else if (error.response.status == 403) {
        Message.error({
            message: '权限不足, 请联系管理员, ㄟ( ▔, ▔ )ㄏ！'
        });
    } else if (error.response.status == 403) {
        Message.error({
            message: '尚未登录, 请先登录, <(￣︶￣)↗[Login!]！'
        });
    } else {
        if (error.response.data.msg) {
            Message.error({
                message: error.response.data.msg
            });
        } else {
            Message.error({
                message: '未知错误, ( ╯□╰ )！'
            });
        }
    }
    return;
});

let base = '';

export const postKeyValueResquest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        transformRequest:[data => {
            let ret = '';
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&';
            }
            console.log(data);
            console.log(ret);
            return ret;
        }],
        headers: {
            'Content-Type' : 'application/x-www-form-urlencoded'
        }
    });
}
