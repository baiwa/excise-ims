import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
declare var jQuery: any;
declare var $: any;
@Component({
    selector: 'create-trader',
    templateUrl: './create-trader.component.html',
    styleUrls: []
})
export class CreateTraderComponent implements OnInit {
  
    constructor(private ajax : AjaxService){

    }
    ngOnInit(): void {
        $('#calendar').calendar({
            type: 'month'
          });   

    }
    onKeyUp = (event: any) => {        
        //console.log(event.keyCode.value % 2);
        var patt1 = /[1-9]/g;
    var result = event.target.value.match(patt1);
    console.log(isNaN(event.target.value));
        if (event.target.value % 2==1||  event.target.value>24||isNaN(event.target.value)){
            event.target.value ='';
        }

    }

    // analyzeData  ( ){
    // //     // this.ajax.post(
    // //     //     'working/test/list',
    // //     //     "{no1 : '15/15',no2 : '10'}",
    // //     //     alert('hahaha'),
    // //     //     ret => console.log(ret)
    // //     //   ).then(
    // //     //     res => console.log(res.json().data),
    // //     //     error => console.log(error)
    // //     //   );

    //     let body = JSON.parse('{"no1" : "15/15","no2" : "10"}');
    //     this.ajax.post('working/test/list', body, (success: Response) => {
    //         console.log("success");
    //     }, (error: Response) => {
    //         let body: any = error.json();
    //         console.log("fail");
    //     });
    // }
    callFn() {
        this.ajax.get(
          'working/test/list?no1=55',    
          alert('success\n** Just alert..!'),
          ret => console.log(ret)
        ).then(
          res => console.log(res.json().data),
          error => console.log(error)
        );
    
    
}
}
