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
          labels: ["January","February","March","April","May"],
          datasets:[
            {
              label: "",
              data: [65,59,80,81,56,],
              fill: false,
              backgroundColor: ["green","blue","red","brown","orange ",],            
            
              borderColor:
                "rgb(0, 51, 0)" ,              
             
              borderWidth: 1

            },
            {
              label: "",
              data: [55,65,50,83,90,74,56],
              fill: false,
              backgroundColor: 
              ["green","blue","red","brown","orange ",],    
            
              borderColor:
                "rgb(77, 77, 255)",                
            
              borderWidth: 1
            },
         
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
