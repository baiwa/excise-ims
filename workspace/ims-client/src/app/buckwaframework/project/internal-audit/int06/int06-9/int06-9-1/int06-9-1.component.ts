import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int06-9-1',
  templateUrl: './int06-9-1.component.html',
  styleUrls: ['./int06-9-1.component.css']
})
export class Int0691Component implements OnInit {

  constructor() { }

  ngOnInit() {
    this.hidedata();

  }

  showdata(){
    $('#int0621').show();
  }

  hidedata(){
    $('#int0621').hide();
  }



}
