import {getRequest} from "./api";

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRoutes = formatRoutes(data);
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
                if (component.startsWith("Emp")) {
                    import('../views/emp/' + component + '.vue');
                } else if (component.startsWith("Per")) {
                    import('../views/per/' + component + '.vue');
                } else if (component.startsWith("Sal")) {
                    import('../views/sal/' + component + '.vue');
                } else if (component.startsWith("sta")) {
                    import('../views/sta/' + component + '.vue');
                } else if (component.startsWith("sys")) {
                    import('../views/sys/' + component + '.vue');
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
