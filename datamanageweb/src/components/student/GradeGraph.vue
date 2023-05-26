<template>
  <div id="main" style="width: 600px; height: 400px;">

  </div>
</template>

<script>
import * as echarts from 'echarts/core';
import { TitleComponent, GridComponent } from 'echarts/components';
import { BarChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
import { onMounted, ref } from 'vue';
echarts.use([TitleComponent, GridComponent, BarChart, CanvasRenderer]);
import $ from 'jquery'
import {useStore} from "vuex";

export default {
  props: ["msg"],
  setup(props) {
    const store = useStore();
    let echart = echarts;

    onMounted(() => {
      let chart = echart.init(document.getElementById("main"));
      let data = ref([]);

      $.ajax({
        url: "https://data.lxcode.xyz/api/student-section/query/grade-graph/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          section_id: props.msg,
        },
        success(resp) {
          data.value = resp;
          chart.setOption({
            title: {
              text: '成绩分布直方图'
            },
            xAxis: {
              type: 'category',
              data: ['不及格', '60~69', '70~79', '80~89', '90+']
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: data.value,
                type: 'bar'
              }
            ]
          });
        }
      });
    });
  }
}

</script>