import {getRequest} from "./api";

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRoutes = formatRoutes(data);
            console.log(fmtRoutes);
            router.addRoutes(fmtRoutes);
            store.commit('initRoutes', fmtRoutes);
        }
    });
}

export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component: () => {
                if (component.startsWith("Home")) {
                    return import('../views/' + component + '.vue');
                } else if (component.startsWith("Emp")) {
                    return import('../views/emp/' + component + '.vue');
                } else if (component.startsWith("Per")) {
                    return import('../views/per/' + component + '.vue');
                } else if (component.startsWith("Sal")) {
                    return import('../views/sal/' + component + '.vue');
                } else if (component.startsWith("sta")) {
                    return import('../views/sta/' + component + '.vue');
                } else if (component.startsWith("sys")) {
                    return import('../views/sys/' + component + '.vue');
                }
            }
            /*
            * component(resolve) {
            *   require(['../views/' + component + '.vue'], resolve)
            * }
            * */
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}
