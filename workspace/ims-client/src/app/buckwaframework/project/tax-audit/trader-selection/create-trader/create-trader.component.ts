import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import { Router } from '@angular/router';

import { TextDateTH, digit } from '../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;
@Component({
    selector: 'create-trader',
    templateUrl: './create-trader.component.html',
    styleUrls: []
})
export class CreateTraderComponent implements OnInit {
  
    constructor(
        private ajax : AjaxService,
        private router: Router
    ){

    }
    ngOnInit(): void {
        $('#calendar').calendar({
            type: 'month',
            text: TextDateTH,
            formatter: {
                date: function (date , settings) {
                    if (!date) return '';
                    let month = date.getMonth();
                    let year = date.getFullYear() + 543;
                    return TextDateTH.months[month] + ' ' + year;
                }
            }
        });
    }

    onSubmit = (event: any) => {
        event.preventDefault();
        let date = event.target['date-raw'].value;
        let date_split = date.split(' ');
        date_split[0] = digit(TextDateTH.months.indexOf(date_split[0]) + 1);
        let date_str = date_split[0] + '/' + date_split[1];
        let num = event.target['num-raw'].value;
        this.router.navigate(
            ['/analyst-basic-data-trader'],
            { queryParams: { from: date_str, month: num } }
        );
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
