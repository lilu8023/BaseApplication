package com.lilu.application.function.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilu.application.R;
import com.lilu.base.update.VersionUpdate;
import com.lilu.base.update.entity.PromptEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdatePrompter;
import com.lilu.base.update.proxy.IUpdateProxy;
import com.lilu.base.update.proxy.impl.DefaultUpdateChecker;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class SocialFragment extends TestFragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_social,container,false);
        return rootView;
    }

    @Override
    public void loadData() {
        Logger.i("SocialFragment 进行了懒加载");

        VersionUpdate.newBuild(getActivity())
                .update();


//        VersionUpdate.newBuild(getActivity())
//                .updateChecker(new DefaultUpdateChecker(){
//                    @Override
//                    public void noNewVersion(Throwable throwable) {
//                        super.noNewVersion(throwable);
//                        Logger.i("暂无新版本");
//                    }
//                })
//                .updatePrompter(new IUpdatePrompter() {
//                    @Override
//                    public void showPrompt(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy, @NonNull PromptEntity promptEntity) {
//                        Logger.i("要显示了");
//                    }
//                }).update();
    }
}
