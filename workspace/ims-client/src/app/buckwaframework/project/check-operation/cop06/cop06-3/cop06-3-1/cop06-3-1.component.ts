import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-cop06-3-1',
  templateUrl: './cop06-3-1.component.html',
  styleUrls: ['./cop06-3-1.component.css']
})
export class Cop0631Component implements OnInit {
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
