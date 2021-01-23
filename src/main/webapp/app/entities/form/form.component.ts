import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IForm } from 'app/shared/model/form.model';
import { FormService } from './form.service';
import { FormDeleteDialogComponent } from './form-delete-dialog.component';

@Component({
  selector: 'jhi-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit, OnDestroy {
  forms?: IForm[];
  eventSubscriber?: Subscription;

  constructor(
    protected formService: FormService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.formService.query().subscribe((res: HttpResponse<IForm[]>) => (this.forms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInForms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IForm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInForms(): void {
    this.eventSubscriber = this.eventManager.subscribe('formListModification', () => this.loadAll());
  }

  delete(form: IForm): void {
    const modalRef = this.modalService.open(FormDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.form = form;
  }
}
