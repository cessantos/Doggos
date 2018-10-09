package cess.com.br.doggos.ui.contracts;

import cess.com.br.doggos.ui.BasePresenter;
import cess.com.br.doggos.ui.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
