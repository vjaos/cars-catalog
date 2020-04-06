<template>
    <v-container>
        <span class="text-uppercase font-weight-medium display-1 deep-purple--text text--lighten-1"
        >
            DB Statistics
        </span>
        <br>
        <div class="stat-col">
            <h2 class="text-uppercase headline font-weight-medium">Stats</h2>
            <ul class="car-stat">
                <li><span>Total car number:</span> {{ dbStat.totalCount }}</li>
                <li><span>First car creation date:</span> {{ dbStat.firstCreated }}</li>
                <li><span>Last car creation date:</span> {{ dbStat.lastCreated }}</li>
                <li><span>Amount of Purple cars:</span> {{ dbStat.amountOfPurple }}</li>
                <li><span>Amount of Red cars:</span>{{ dbStat.amountOfRed }}</li>
            </ul>
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "StatisticsPage",
        computed: {
            dbStat() {
                return this.$store.getters.dbStat
            }
        },
        created() {
            this.$store.dispatch('getStat')
        },
    }

</script>

<style scoped>
    .stat-col {
        width: 33.33%;
        height: 200px;
        float: left;
        padding: 10px;
        position: relative;
    }

    .car-stat li {
        display: block;
        height: 30px;
        line-height: 30px;
        color: #424242;
    }

    .car-stat li span {
        display: inline-block;
        width: 60%;
        color: #424242;
    }

</style>