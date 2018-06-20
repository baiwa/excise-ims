import { Component, AfterViewInit } from '@angular/core';
import * as Chart from 'chart.js';
declare var $: any;
@Component({
  selector: 'app-mgc01-2',
  templateUrl: './mgc01-2.component.html',
  styleUrls: ['./mgc01-2.component.css']
})
export class Mgc012Component implements AfterViewInit {


  canvas: any;
  ctx: any;
  constructor() { }

  ngAfterViewInit() {
    this.canvas = document.getElementById('myChart2');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
        type: "bar",
        data: {
          labels: ["January","February","March","April","May","June","July"],
          datasets:[
            {
              label: "A",
              data: [65,59,80,81,56,55,40],
              fill: false,
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 99, 132, 0.2)",
              ],
              borderColor: [
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
                "rgb(255, 99, 132)",
              ],
              borderWidth: 1
            },
            {
              label: "B",
              data: [55,65,50,83,90,74,56],
              fill: false,
              backgroundColor: [
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
                "rgb(255, 159, 64)",
              ],
              borderWidth: 1
            },
            // {
            //   label: "My First Dataset",
            //   data: [65,59,80,81,56,55,40],
            //   fill: false,
            //   backgroundColor: [
            //     "rgba(255, 99, 132, 0.2)",
            //     "rgba(255, 159, 64, 0.2)",
            //     "rgba(255, 205, 86, 0.2)",
            //     "rgba(75, 192, 192, 0.2)",
            //     "rgba(54, 162, 235, 0.2)",
            //     "rgba(153, 102, 255, 0.2)",
            //     "rgba(201, 203, 207, 0.2)"
            //   ],
            //   borderColor: [
            //     "rgb(255, 99, 132)",
            //     "rgb(255, 159, 64)",
            //     "rgb(255, 205, 86)",
            //     "rgb(75, 192, 192)",
            //     "rgb(54, 162, 235)",
            //     "rgb(153, 102, 255)",
            //     "rgb(201, 203, 207)"
            //   ],
            //   borderWidth: 1
            // }
          ]
        },
        options: {
          responsive: false,
          display: true,
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true
                }
              }
            ]
          }
        }
      });
  }

}
