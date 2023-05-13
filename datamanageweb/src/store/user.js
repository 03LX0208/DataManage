import $ from 'jquery';

export default {
    state: {
        id: "",
        username: "",
        token: "",
        is_login: false,
    },
    getters: {

    },
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.is_login = user.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.token = "";
            state.is_login = false;
            localStorage.setItem("jwt_token", state.token);
        },
    },
    actions: {
        login(context, data) {
            $.ajax({
                url: "https://data.lxcode.xyz/api/user/token/",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateToken", resp.token);
                        localStorage.setItem("jwt_token", resp.token);
                        data.success();
                    } else data.error();
                },
                error(resp) {
                    data.error(resp);
                }
            });
        },
        getInfo(context, data) {
            $.ajax({
                url: "https://data.lxcode.xyz/api/user/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    context.commit("updateUser", {
                        ...resp,
                        is_login: true,
                    });
                    data.success();
                },
                error() {
                    data.error();
                }
            });
        },
        logout(context, data) {
            context.commit("logout");
            data.success();
        }
    },
    modules: {

    }
}