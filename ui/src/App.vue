<template>
    <v-app>
        <v-content>
            <cars-list/>
        </v-content>
    </v-app>
</template>

<script>


    import CarsList from "./components/CarsList";

    export default {
        name: 'App',
        components: {CarsList},
        data: () => ({
            //
        }),
    };
</script>
