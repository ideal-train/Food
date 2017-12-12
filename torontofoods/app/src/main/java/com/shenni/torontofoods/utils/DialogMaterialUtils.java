package com.shenni.torontofoods.utils;

import android.content.Context;
import android.text.InputType;
import android.view.View;

import com.shenni.torontofoods.R;
import com.weavey.utils.ContentAdapter;
import com.weavey.utils.ContentItemListener;
import com.wevey.selector.dialog.EditDialog;
import com.wevey.selector.dialog.IMGDialog;
import com.wevey.selector.dialog.ListDialog;
import com.wevey.selector.dialog.SelectDialogInterface;
import com.wevey.selector.dialog.TipsDialog;

import java.util.ArrayList;

import static com.shenni.torontofoods.utils.ToastUtil.toast;


/**
 * Created by Administrator on 2017/8/11.
 * function：
 */

public class DialogMaterialUtils {

    /**
     * 弹窗：输入框 两个按钮
     *
     * @param context
     */
    private void initEditDialog(Context context) {

        new EditDialog.Builder(context).setTitleVisible(true)
                .setTitleText("修改用户名")
//                .setTitleTextSize(20)
//                .setTitleTextColor(R.color.black_light)
                .setContentText("")
                .setContentTextSize(16)
                .setMaxLength(80)
                .setHintText("网页链接")
                .setMaxLines(4)
                .setContentText("默认输入")
//                .setContentTextColor(R.color.colorPrimary)
                .setButtonTextSize(14)
                .setLeftButtonTextColor(R.color.colorPrimary)
                .setLeftButtonText("取消")
                .setRightButtonTextColor(R.color.colorPrimary)
                .setRightButtonText("确定")
//                .setLineColor(R.color.no_color)
                .setInputTpye(InputType.TYPE_CLASS_TEXT)
                .setOnclickListener(new SelectDialogInterface.OnLeftAndRightClickListener<EditDialog>
                        () {

                    @Override
                    public void clickLeftButton(EditDialog dialog, View view) {

                        toast(dialog.getEditTextContent());
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(EditDialog dialog, View view) {

                        toast(dialog.getEditTextContent() + "确定");
                        dialog.dismiss();
                    }

                    @Override
                    public void clickColseButton(EditDialog dialog, View view) {

                    }
                })
                .setMinHeight(0.3f)
                .setWidth(0.8f)
                .build()
                .show();
    }


    /**
     * 弹窗：仅仅提示 两个按钮 中标提示
     *
     * @param context
     */
    private void initNormalDialog(Context context) {

        new TipsDialog.Builder(context).setTitleVisible(false)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("这是提示")
                .setBid(false)//显示中标
                //------------一个按钮-------------
                .setSingleMode(true)//显示中标
                //-------------------------
                .setContentTextColor(R.color.black_light)
//                .setLeftButtonText("关闭")
                .setLeftButtonTextColor(R.color.black_light)
//                .setLeftButtonTextColor(R.color.gray)
//                .setRightButtonText("不关闭")
                .setRightButtonTextColor(R.color.colorPrimary)
                .setOnclickListener(new SelectDialogInterface.OnLeftAndRightClickListener<TipsDialog>() {
                    @Override
                    public void clickLeftButton(TipsDialog dialog, View view) {

                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(TipsDialog dialog, View view) {

                        dialog.dismiss();
                    }

                    @Override
                    public void clickColseButton(TipsDialog dialog, View view) {

                    }
                })
                .setSingleListener(new SelectDialogInterface.OnSingleClickListener<TipsDialog>() {
                    @Override
                    public void clickJustOneleButton(TipsDialog dialog, View view) {
//只有一个按钮 的点击事件
                    }
                })
                .build()
                .show();

    }


    /**
     * 焦币不足
     * 赠送焦比提示 添加:setJustLeftButton
     *
     * @param context
     */
    private void initMDDialog(Context context) {

        new IMGDialog.Builder(context)
//                .setHeight(0.21f)  //屏幕高度*0.21
//                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText("余额不足")
                .setTitleTextColor(R.color.font_orange)
                .setContentText("是否充值")
                //------------赠送焦比提示Start---------------
                .setJustLeftButton(true)
                .setContentText2("总资源月赠予8%的焦比")
                //------------赠送焦比提示End---------------
//                .setContentTextColor(R.color.black_light)
//                .setLeftButtonText("不发送")
                .setLeftButtonTextColor(R.color.colorAccent)
//                .setRightButtonText("发送")
                .setRightButtonTextColor(R.color.colorAccent)
//                .setTitleTextSize(16)
//                .setContentTextSize(14)
//                .setButtonTextSize(14)
                .setOnclickListener(new SelectDialogInterface.OnLeftAndRightClickListener<IMGDialog>() {

                    @Override
                    public void clickLeftButton(IMGDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(IMGDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickColseButton(IMGDialog dialog, View view) {
                        toast("关闭");
                    }
                })
                .build()
                .show();

    }


    ContentItemListener contentItemListener;

    /**
     * list弹窗
     *
     * @param context
     */
    public void initBottomDialog(Context context) {

        ArrayList<String> s = new ArrayList<>();
        s.add("Weavey0");
        s.add("Weavey1");
        s.add("Weavey2");
        s.add("Weavey3");
//
//        new NormalSelectionDialog.Builder(this).setlTitleVisible(true)   //设置是否显示标题
//                .setTitleHeight(0)   //设置标题高度
//                .setTitleText("please select")  //设置标题提示文本
//                .setTitleTextSize(14) //设置标题字体大小 sp
//                .setTitleTextColor(R.color.font_black) //设置标题文本颜色
//                .setItemHeight(40)  //设置item的高度
//                .setItemWidth(0.9f)  //屏幕宽度*0.9
//                .setItemTextColor(R.color.font_blue)  //设置item字体颜色
//                .setItemTextSize(14)  //设置item字体大小
//                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
//                .setOnItemListener(new SelectDialogInterface.OnItemClickListener<NormalSelectionDialog>() {
//
//                    @Override
//                    public void onItemClick(NormalSelectionDialog dialog, View button, int
//                            position) {
//
//                        dialog.dismiss();
//                    }
//                })
//                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
//                .build()
//                .setDatas(s)
//                .show();


        contentItemListener = new ContentItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                toast("onItemClick:" + position);
            }
        };

        ContentAdapter contentAdapter = new ContentAdapter(context, s, contentItemListener);
//        ContentAdapter contentAdapter = new ContentAdapter(context, s, this);
        new ListDialog.Builder(context)
                .setContentAdapter(contentAdapter)
                .setBottom("取消")
                .setBottomVisivle(true)
                .build()
                .show();
    }


//    private void WxImagePicker() {
//        List<String> names = new ArrayList<>();
//        names.add("拍摄照片");
//        names.add("选择手机中图片");
//        showDialog(new SelectDialog.SelectDialogListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0: // 直接调起相机
//                        /**
//                         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
//                         *
//                         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
//                         *
//                         * 如果实在有所需要，请直接下载源码引用。
//                         */
//                        //打开选择,本次允许选择的数量
//                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                        Intent intent = new Intent(CenterActivity.this, ImageGridActivity.class);
//                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//                        startActivityForResult(intent, REQUEST_CODE_SELECT);
//                        break;
//                    case 1:
//                        //打开选择,本次允许选择的数量
//                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                        Intent intent1 = new Intent(CenterActivity.this, ImageGridActivity.class);
//                                /* 如果需要进入选择的时候显示已经选中的图片，
//                                 * 详情请查看ImagePickerActivity
//                                 * */
////                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
//                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//        }, names);
//
//
//    }
//
//    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
//        SelectDialog dialog = new SelectDialog(this, R.style
//                .transparentFrameWindowStyle,
//                listener, names);
//        if (!this.isFinishing()) {
//            dialog.show();
//        }
//        return dialog;
//    }

}
