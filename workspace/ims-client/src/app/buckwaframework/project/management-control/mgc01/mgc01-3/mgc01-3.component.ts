import { Component, AfterViewInit } from "@angular/core";

import * as Chart from "chart.js";

declare var $: any;

@Component({
  selector: "app-mgc01-3",
  templateUrl: "./mgc01-3.component.html",
  styleUrls: ["./mgc01-3.component.css"]
})
export class Mgc013Component implements AfterViewInit {
  canvas: any;
  ctx: any;

  constructor() {}

  ngAfterViewInit() {
    this.canvas = <HTMLCanvasElement>document.getElementById("myChart3");
    this.ctx = this.canvas.getContext("2d");
    let myChart = new Chart(this.ctx, {
      type: "pie",
      data: {
        labels: ["A", "B", "C", "D", "E"],
        datasets: [
          {
            label: "Result",
            data: [4, 6, 3, 7, 3],
            backgroundColor: [
              "rgb(0, 204, 0)",
              "rgb(0, 0, 255)",
              "rgb(255, 0, 0)",
              "rgb(102, 0, 0)",
              "rgb(255, 102, 0)"
            ],
            borderWidth: 1
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
