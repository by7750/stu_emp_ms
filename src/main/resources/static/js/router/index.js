import * as VueRouter from '../vue-router.global.js'

const routes = [
    {
        path: '/users',
        template: '<h1>用户列表</h1>'
    }
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes
})

export default router