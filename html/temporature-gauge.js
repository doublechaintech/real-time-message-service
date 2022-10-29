option = {
    series: [
      {
        type: 'gauge',
        min: 0,
        max: 80,
        axisLine: {
          lineStyle: {
            width: 30,
            color: [
              [0.625, 'green'],
              [0.75, '#CCCC00'],
              [1, 'red']
            ]
          }
        },
        pointer: {
          itemStyle: {
            color: 'auto'
          }
        },
        axisTick: {
          distance: -30,
          length: 8,
          lineStyle: {
            color: '#fff',
            width: 2
          }
        },
        splitLine: {
          distance: -30,
          length: 30,
          lineStyle: {
            color: '#fff',
            width: 4
          }
        },
        axisLabel: {
          color: 'auto',
          distance: 40,
          fontSize: 20
        },
        detail: {
          valueAnimation: true,
          formatter: '{value} °C',
          color: 'auto'
        },
        data: [
          {
            value: 70
          }
        ]
      }
    ]
  };
  setInterval(function () {
    myChart.setOption({
      series: [
        {
          data: [
            {
              value: +(Math.random() * 80).toFixed(2)
            }
          ]
        }
      ]
    });
  }, 2000);