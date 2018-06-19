import { Component, AfterViewInit } from '@angular/core';
import * as Chart from 'chart.js';
declare var $: any;
@Component({
  selector: 'app-mgc01-1',
  templateUrl: './mgc01-1.component.html',
  styleUrls: ['./mgc01-1.component.css']
})
export class Mgc011Component implements AfterViewInit {


  canvas: any;
  ctx: any;
  constructor() { }

  ngAfterViewInit() {
    this.canvas = document.getElementById('myChart1');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'line',
      data: {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
          {
            label: "A",
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            borderColor: "green",
            lineTension: 0.1
          },
          {
            label: "B",
            data: [60, 75, 55, 60, 84, 66, 44],
            fill: false,
            borderColor: "yellow",
            lineTension: 0.1
          }
        ]
      },
      options: {
        responsive: false,
        display: true
      }
    });
  }

}
