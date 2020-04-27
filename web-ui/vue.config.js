let proxyObj = {};
proxyObj['/'] = {
    ws: false,  // 关闭对 websocket 的转发处理.
    target: 'http://localhost:8081',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}
