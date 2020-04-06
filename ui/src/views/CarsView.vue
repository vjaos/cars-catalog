<template>
    <v-container fluid class="ml-5">
        <cars-table></cars-table>
    </v-container>

</template>

<script>
    import CarsTable from "../components/CarsTable";

    export default {
        name: "CarsPage",
        components: {CarsTable}
    }
</script>

<style scoped>

</style>