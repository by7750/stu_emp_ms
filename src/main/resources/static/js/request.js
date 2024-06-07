(function (win) {
    axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
    // 创建axios实例
    const service = axios.create({
        // axios中请求配置有baseURL选项，表示请求URL公共部分
        baseURL: '/',
        // 超时
        timeout: 1000000
    })

    // 响应拦截器
    service.interceptors.response.use(res => {
            console.log('---响应拦截器---', res)
            // 未设置状态码则默认成功状态
            const code = res.data.status;
            // 获取错误信息
            const msg = res.data.msg
            console.log('---code---', code)
            if (code === 400) {// 返回登录页面
                console.log('---/backend/page/login/login.html---', code)
                localStorage.removeItem('userInfo')
                window.top.location.href = '/pages/login/login.html'
            } else {
                return res.data
            }
        },
        error => {
            console.log('err' + error)
            return Promise.reject(error)
        }
    )
    win.$axios = service
})(window);
