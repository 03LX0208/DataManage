<template>
  <div style="position: relative; width: 100%; height: 100%;">
    <div style="position: relative; width: 100%; height: 100%;">
      <div style="width: 100%;">
        <NavBar/>
      </div>
      <div style="width: 100%; text-align: center;">
        <div id="main" style="display: inline-block; width: 600px; height: 600px"></div>
      </div>
    </div>
  </div>
  <n-date-picker type="date" v-model:value="time" @update:value="print" />
</template>

<script>
import NavBar from "@/components/NavBar";
import { onMounted, ref } from "vue";
import * as echarts from 'echarts/core';
import { TitleComponent, TooltipComponent, TimelineComponent, GridComponent  } from 'echarts/components';
import { GraphChart, BarChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
import { NDatePicker } from 'naive-ui'

echarts.use([TitleComponent, TooltipComponent, GraphChart, CanvasRenderer, TimelineComponent, GridComponent, BarChart]);

export default {
  components: {
    NavBar,
    NDatePicker,
  },
  setup() {
    let time = ref(null);

    const dataChange = () => {
      const date = new Date(time.value);
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      const formattedDate = `${year}-${month}-${day}`;
      console.log(formattedDate);

      const nowDate = new Date();
      const nowYear = nowDate.getFullYear();
      const nowMonth = nowDate.getMonth();
      const nowDay = nowDate.getDate();
      const NOW = new Date(nowYear, nowMonth, nowDay).getTime();
      if (time.value > NOW) time.value = NOW;
    }

    let echart = echarts;
    onMounted(() => {
      let chart = echart.init(document.getElementById('main'));
      chart.setOption({
        timeline: {
          data: ['2019', '2020', '2021']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: ['A', 'B', 'C', 'D']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '2019',
            type: 'bar',
            data: [10, 20, 30, 40]
          },
          {
            name: '2020',
            type: 'bar',
            data: [15, 25, 35, 45]
          },
          {
            name: '2021',
            type: 'bar',
            data: [20, 30, 40, 50]
          }
        ]
        })
      });
      // let data = [
      //   {
      //     name: '离散数学',
      //     x: 300,
      //     y: 300
      //   },
      //   {
      //     name: 'C++',
      //     x: 800,
      //     y: 300
      //   },
      //   {
      //     name: '微积分AI',
      //     x: 550,
      //     y: 100
      //   },
      //   {
      //     name: '数据结构',
      //     x: 550,
      //     y: 500
      //   }
      // ];
      // let links = [
      //   {
      //     source: '微积分AI',
      //     target: '离散数学'
      //   },
      //   {
      //     source: '离散数学',
      //     target: '数据结构'
      //   },
      //   {
      //     source: 'C++',
      //     target: '数据结构'
      //   },
      // ];
    return {
      time,
      dataChange,
    }
  }
}
</script>

<style scoped>

</style>