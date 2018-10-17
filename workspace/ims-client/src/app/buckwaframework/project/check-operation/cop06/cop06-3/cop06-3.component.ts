import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-cop06-3',
  templateUrl: './cop06-3.component.html',
  styleUrls: ['./cop06-3.component.css']
})
export class Cop063Component implements OnInit {

  constructor() { }

  ngOnInit() {
    this.hidedata();
  }


  showdata() {
    $("#showData").show();
  }
  hidedata() {
    $("#showData").hide();
  }
}
