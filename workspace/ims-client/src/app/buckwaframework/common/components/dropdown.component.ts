import { Component, Input, ElementRef, ViewChild } from '@angular/core';

import { Dropdown } from '../../common/models/dropdown';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'dropdown',
    template: `
    <div #dropdown class="ui selection dropdown">
        <div class="default text">{{(this.datas? this.datas[0].text : '')}}</div>
        <i class="dropdown icon"></i>
    </div>
    `
})
export class DropdownComponent {

    @ViewChild('dropdown') el: ElementRef;

    @Input() placeholder: string;
    @Input() datas: Dropdown[];

    ngAfterViewInit() {

        $(this.el.nativeElement).dropdown({
            values: this.datas,
            placeholder: this.placeholder
        });
    }
}