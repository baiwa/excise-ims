import { Component, OnInit } from '@angular/core';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-cop06-1-2',
  templateUrl: './cop06-1-2.component.html',
  styleUrls: ['./cop06-1-2.component.css']
})
export class Cop0612Component implements OnInit {

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
