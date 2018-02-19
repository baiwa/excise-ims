import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-7',
  templateUrl: './int07-7.component.html',
  styleUrls: ['./int07-7.component.css']
})
export class Int077Component implements OnInit {

  constructor() { }


  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }
}
