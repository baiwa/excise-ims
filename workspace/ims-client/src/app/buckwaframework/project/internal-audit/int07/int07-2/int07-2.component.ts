import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-2',
  templateUrl: './int07-2.component.html',
  styleUrls: ['./int07-2.component.css']
})
export class Int072Component implements OnInit {

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
