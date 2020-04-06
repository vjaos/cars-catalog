import Vue from 'vue'
import VueRouter from "vue-router";
import CarsView from "./views/CarsView";
import StatisticsView from "./views/StatisticsView";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'cars',
        component: CarsView
    },
    {
        path: '/statistics',
        name: 'statistics',
        component: StatisticsView
    }
]

export default new VueRouter({
    mode: 'history',
    routes
})