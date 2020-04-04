import Vue from 'vue'
import VueRouter from "vue-router";
import CarsPage from "./views/CarsPage";
import StatisticsPage from "./views/StatisticsPage";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'cars',
        component: CarsPage
    },
    {
        path: '/statistics',
        name: 'statistics',
        component: StatisticsPage
    }
]

export default new VueRouter({
    mode: 'history',
    routes
})