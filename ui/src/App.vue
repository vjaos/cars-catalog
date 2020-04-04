<template>
    <v-app>
        <v-navigation-drawer app
                             permanent
                             clipped
                             mini-variant
                             mini-variant-width="90px"

        >
            <v-list flat>
                <v-list-item
                        v-for="item in items"
                        :key="item.key"
                        :to="item.route"
                        active-class="dash deep-purple--text text--lighten-1"
                >
                    <v-list-item-action>
                        <v-icon v-text="item.icon"/>
                    </v-list-item-action>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-content>
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>


    export default {
        name: 'App',
        data: () => ({
            items: [
                {title: 'Dashboard', icon: 'mdi-view-dashboard', route: '/'},
                {title: 'Statistics', icon: 'mdi-clipboard-text-outline', route: '/statistics'}
            ]
        }),
    };
</script>
<style>
    .dash {
        border-left: 7px solid #7E57C2;
    }
</style>