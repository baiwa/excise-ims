/**
 * CREATED BY: KimJaeHa
 * DATE: 21 AUG 2018 16:13
 */
import { Component, Input, ElementRef, ViewChild, Output, EventEmitter } from '@angular/core';

declare var $: any;
@Component({
    selector: 'pagination',
    template: `
    <div #paginate class="ui {{ class }} mini pagination menu">
        <a *ngIf="active !== 1" (click)="prev()" class="icon item">
            <i class="left chevron icon"></i>
        </a>
        <a *ngIf="active === 1" class="icon item disabled">
            <i class="left chevron icon"></i>
        </a>
        <a (click)="change(n.num)" class="{{ n.active ? 'active ' : '' }}item" *ngFor="let n of datas">{{n.num}}</a>
        <a (click)="next()" *ngIf="active !== pages" class="icon item">
            <i class="right chevron icon"></i>
        </a>
        <a *ngIf="active === pages" class="icon item disabled">
            <i class="right chevron icon"></i>
        </a>
    </div>
    `
})
export class PaginationComponent {
    // ViewChild
    @ViewChild('paginate') el: ElementRef;
    // Output
    @Output() length: EventEmitter<number> = new EventEmitter<number>();
    // Input
    @Input() active: number;
    @Input() step: number;
    @Input() pages: number;
    // Class
    @Input() class: string;

    datas: any[] = [];

    ngOnChanges() {
        const paging = { range: this.pages < 5 ? this.pages : 5, pages: this.pages };
        this.datas = this.doPaging(this.active, paging);
    }

    prev() { // Prev Value
        const { active, step } = this;
        const num = (active * step) - step;
        this.length.emit(num);
    }

    change(n: number) { // Change Value
        const { step } = this;
        const num = (n * step);
        this.length.emit(num);
    }

    next() { // Next Value
        const { active, step } = this;
        const num = (active * step) + step;
        this.length.emit(num);
    }

    doPaging(current, { range, pages, start = 1 }) {
        const paging = [];
        var i = Math.min(pages + start - range, Math.max(start, current - (range / 2 | 0)));
        const end = range - i < pages ? i + range : i + 1;
        while (i < end) {
            paging.push(
                i == current ?
                    { num: i++, active: true, hidden: false }: { num: i++, active: false, hidden: false}
            )
        }
        return paging;
    }

}