//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.e4a.runtime;

import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;
import com.e4a.runtime.ColorPickerDialog.OnColorChangedListener;
import com.e4a.runtime.android.mainActivity;
import com.e4a.runtime.annotations.SimpleFunction;
import com.e4a.runtime.annotations.SimpleObject;
import com.e4a.runtime.parameters.BooleanReferenceParameter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SimpleObject
public final class 对话框类 {
    private static ProgressDialog progressDialog;
    private static boolean 可取消 = false;
    private static 对话框类.MyAdapter Adapter;
    private static List<Map<String, String>> data;
    private static int InputType = 4;
    private static String OK = "确定";
    private static String CANCEL = "取消";

    private 对话框类() {
    }

    @SimpleFunction
    public static void 设置对话框语言类型(int value) {
        if (value == 1) {
            OK = "确定";
            CANCEL = "取消";
        } else {
            OK = "OK";
            CANCEL = "Cancel";
        }

    }

    @SimpleFunction
    public static void 设置进度对话框可取消(boolean value) {
        可取消 = value;
    }

    @SimpleFunction
    public static void 显示进度对话框(String msg) {
        progressDialog = ProgressDialog.show(mainActivity.getContext(), "", msg, true, 可取消);
    }

    @SimpleFunction
    public static void 设置进度对话框信息(String msg) {
        if (progressDialog != null) {
            progressDialog.setMessage(msg);
        }

    }

    @SimpleFunction
    public static void 关闭进度对话框() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    @SimpleFunction
    public static void 显示进度对话框2(String title, String msg, boolean cancelable) {
        progressDialog = new ProgressDialog(mainActivity.getContext());
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(cancelable);
        progressDialog.setProgressStyle(1);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @SimpleFunction
    public static void 设置进度对话框进度(int progress) {
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }

    }

    @SimpleFunction
    public static int 信息框(String title, String msg, String btnOK) {
        Builder builder = new Builder(mainActivity.getContext());
        对话框类.MessageBox mb = new 对话框类.MessageBox();
        return mb.showDialog(builder, title, msg, btnOK);
    }

    @SimpleFunction
    public static int 信息框2(String title, String msg, String btnOK, String btnNO) {
        Builder builder = new Builder(mainActivity.getContext());
        对话框类.MessageBox mb = new 对话框类.MessageBox();
        return mb.showDialog2(builder, title, msg, btnOK, btnNO);
    }

    @SimpleFunction
    public static int 信息框3(String title, String message1, String message2, String btnOK, String btnNO, boolean state, BooleanReferenceParameter accept) {
        Builder builder = new Builder(mainActivity.getContext());
        对话框类.MessageBox3 mb = new 对话框类.MessageBox3();
        return mb.showDialog(builder, title, message1, message2, btnOK, btnNO, state, accept);
    }

    @SimpleFunction
    public static String 单选对话框(String title, String[] items, boolean[] state) {
        Builder singleBuilder = new Builder(mainActivity.getContext());
        对话框类.DialogBox db = new 对话框类.DialogBox();
        return db.showDialog(singleBuilder, title, items, state);
    }

    @SimpleFunction
    public static String 多选对话框(String title, String[] items, boolean[] state) {
        Builder multiBuilder = new Builder(mainActivity.getContext());
        对话框类.DialogBox db = new 对话框类.DialogBox();
        return db.showDialog2(multiBuilder, title, items, state);
    }

    @SimpleFunction
    public static void 置输入框输入方式(int method) {
        InputType = method;
    }

    @SimpleFunction
    public static String 输入框(String title, String text, BooleanReferenceParameter accept) {
        Builder builder = new Builder(mainActivity.getContext());
        对话框类.InputBox ib = new 对话框类.InputBox();
        return ib.showDialog(builder, title, text, accept);
    }

    @SimpleFunction
    public static String 密码输入框(String 标题, String 项目一, String 项目二, String 初始内容, String 提示内容, BooleanReferenceParameter 输入结果) {
        Builder builder = new Builder(mainActivity.getContext());
        对话框类.InputBox ib = new 对话框类.InputBox();
        return ib.showDialog2(builder, 标题, 项目一, 项目二, 初始内容, 提示内容, 输入结果);
    }

    private static boolean 是否为竖屏(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    @SimpleFunction
    public static int 颜色选择框(String title, int initalColor) {
        对话框类.ColorBox cb = new 对话框类.ColorBox();
        return cb.showDialog(title, initalColor);
    }

    @SimpleFunction
    public static void 置日期选择框初始日期(int year, int monthOfYear, int dayOfMonth) {
        mainActivity.初始年 = year;
        mainActivity.初始月 = monthOfYear - 1;
        mainActivity.初始日 = dayOfMonth;
    }

    @SimpleFunction
    public static void 显示日期选择框() {
        mainActivity.getContext().showDialog(0);
    }

    @SimpleFunction
    public static void 置时间选择框初始时间(int hourOfDay, int minute) {
        mainActivity.初始时 = hourOfDay;
        mainActivity.初始分 = minute;
    }

    @SimpleFunction
    public static void 显示时间选择框() {
        mainActivity.getContext().showDialog(1);
    }

    private static class ColorBox {
        private int dialogResult;
        private Handler mHandler;

        private ColorBox() {
        }

        public int getDialogResult() {
            return this.dialogResult;
        }

        public void setDialogResult(int dialogResult) {
            this.dialogResult = dialogResult;
        }

        public void endDialog(int result) {
            this.setDialogResult(result);
            Message m = this.mHandler.obtainMessage();
            this.mHandler.sendMessage(m);
        }

        public int showDialog(String title, int initalColor) {
            ColorPickerDialog dialog = new ColorPickerDialog(mainActivity.getContext(), title, 对话框类.OK, initalColor, new OnColorChangedListener() {
                public void colorChanged(int color) {
                    ColorBox.this.endDialog(color);
                }
            });
            dialog.setCancelable(false);
            dialog.show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var5) {
                var5.printStackTrace();
            }

            return this.dialogResult;
        }
    }

    private static class MyAdapter extends BaseAdapter {
        private MyAdapter() {
        }

        public int getCount() {
            return 对话框类.data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return (long)position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView result = (TextView)convertView;
            if (result == null) {
                result = new TextView(mainActivity.getContext());
                result.setPadding(15, 15, 15, 15);
                result.setGravity(4);
            }

            result.setText((CharSequence)((Map)对话框类.data.get(position)).get("title"));
            result.setTextSize(0, 应用操作.转换字体大小(9.0F));
            return result;
        }
    }

    public static class InputBox {
        private String dialogResult;
        private Handler mHandler;

        public InputBox() {
        }

        public String getDialogResult() {
            return this.dialogResult;
        }

        public void setDialogResult(String dialogResult) {
            this.dialogResult = dialogResult;
        }

        public void endDialog(String result) {
            this.setDialogResult(result);

        }

        public String showDialog(Builder builder, String title, String text, final BooleanReferenceParameter accept) {
            final EditText inputServer = new EditText(mainActivity.getContext());
            switch(对话框类.InputType) {
                case 1:
                    inputServer.setInputType(1);
                    break;
                case 2:
                    inputServer.setInputType(3);
                    break;
                case 3:
                    inputServer.setInputType(0);
                    break;
                case 4:
                    inputServer.setInputType(131073);
            }

            inputServer.setText(text);
            inputServer.setFocusable(true);
            builder.setTitle(title).setView(inputServer).setCancelable(false).setPositiveButton(对话框类.OK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    accept.set(true);
                    String inputtext = inputServer.getText().toString();
                    InputBox.this.endDialog(inputtext);
                }
            }).setNegativeButton(对话框类.CANCEL, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    accept.set(false);
                    InputBox.this.endDialog("");
                }
            }).show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {

                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var8) {
                var8.printStackTrace();
            }

            return this.dialogResult;
        }
        public String showDialog(Builder builder, String title, String text, Handler handler) {
            final EditText inputServer = new EditText(mainActivity.getContext());
            switch(对话框类.InputType) {
                case 1:
                    inputServer.setInputType(1);
                    break;
                case 2:
                    inputServer.setInputType(3);
                    break;
                case 3:
                    inputServer.setInputType(0);
                    break;
                case 4:
                    inputServer.setInputType(131073);
            }

            inputServer.setText(text);
            inputServer.setFocusable(true);
            builder.setTitle(title).setView(inputServer).setCancelable(false).setPositiveButton(对话框类.OK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String inputtext = inputServer.getText().toString();
                    InputBox.this.endDialog(inputtext);

                    handler.handleMessage(null);

                }
            }).setNegativeButton("默认", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    InputBox.this.endDialog("");
                    handler.handleMessage(null);
                }
            }).show();
            return this.dialogResult;
        }

        public String showDialog2(Builder builder, String 标题, String 项目一, String 项目二, String 初始内容, String 提示内容, final BooleanReferenceParameter 输入结果) {
            LinearLayout layout = new LinearLayout(mainActivity.getContext());
            layout.setOrientation(1);
            LayoutParams param = new LayoutParams(-1, -1);
            param.weight = 1.0F;
            ListView listview = new ListView(mainActivity.getContext());
            listview.setFocusable(true);
            对话框类.data = new ArrayList();
            对话框类.Adapter = new 对话框类.MyAdapter();
            listview.setAdapter(对话框类.Adapter);
            Map<String, String> map1 = new HashMap();
            map1.put("title", 项目一);
            对话框类.data.add(map1);
            Map<String, String> map2 = new HashMap();
            map2.put("title", 项目二);
            对话框类.data.add(map2);
            对话框类.Adapter.notifyDataSetChanged();
            final EditText inputServer = new EditText(mainActivity.getContext());
            inputServer.setFocusable(true);
            inputServer.setInputType(1);
            inputServer.setTransformationMethod(PasswordTransformationMethod.getInstance());
            inputServer.setText(初始内容);
            inputServer.setHint(提示内容);
            layout.addView(listview, param);
            layout.addView(inputServer, param);
            builder.setTitle(标题).setView(layout).setCancelable(false).setPositiveButton(对话框类.OK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    输入结果.set(true);
                    String inputtext = inputServer.getText().toString();
                    InputBox.this.endDialog(inputtext);
                }
            }).setNegativeButton(对话框类.CANCEL, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    输入结果.set(false);
                    InputBox.this.endDialog("");
                }
            }).show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var16) {
                var16.printStackTrace();
            }

            return this.dialogResult;
        }
    }

    private static class DialogBox {
        private String dialogResult;
        private String[] Items;
        private boolean[] State;
        private Handler mHandler;

        private DialogBox() {
        }

        public String getDialogResult() {
            return this.dialogResult;
        }

        public void setDialogResult(String dialogResult) {
            this.dialogResult = dialogResult;
        }

        public String[] getItems() {
            return this.Items;
        }

        public void setItems(String[] items) {
            this.Items = items;
        }

        public boolean[] getState() {
            return this.State;
        }

        public void setState(boolean[] state) {
            this.State = state;
        }

        public void endDialog(String result) {
            this.setDialogResult(result);
            Message m = this.mHandler.obtainMessage();
            this.mHandler.sendMessage(m);
        }

        public String showDialog(Builder builder, String title, String[] items, boolean[] state) {
            this.Items = items;
            this.State = state;
            int checkedItem = 0;

            for(int k = 0; k < state.length; ++k) {
                if (state[k]) {
                    checkedItem = k;
                    break;
                }
            }

            builder.setTitle(title).setCancelable(false);
            builder.setSingleChoiceItems(items, checkedItem, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    boolean[] s = DialogBox.this.getState();

                    for(int i = 0; i < s.length; ++i) {
                        if (i == which) {
                            s[i] = true;
                        } else {
                            s[i] = false;
                        }
                    }

                    DialogBox.this.setState(s);
                }
            });
            builder.setPositiveButton(对话框类.OK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String result = "";
                    boolean[] s = DialogBox.this.getState();
                    String[] it = DialogBox.this.getItems();

                    for(int i = 0; i < s.length; ++i) {
                        if (s[i]) {
                            result = it[i];
                        }
                    }

                    DialogBox.this.endDialog(result);
                }
            });
            builder.setNegativeButton(对话框类.CANCEL, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    DialogBox.this.endDialog("");
                }
            });
            builder.show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var7) {
                var7.printStackTrace();
            }

            return this.dialogResult;
        }

        public String showDialog2(Builder builder, String title, String[] items, boolean[] state) {
            this.Items = items;
            this.State = state;
            builder.setTitle(title).setCancelable(false);
            builder.setMultiChoiceItems(items, state, new OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    boolean[] s = DialogBox.this.getState();
                    s[which] = isChecked;
                    DialogBox.this.setState(s);
                }
            });
            builder.setPositiveButton(对话框类.OK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String result = "";
                    boolean[] s = DialogBox.this.getState();
                    String[] it = DialogBox.this.getItems();

                    for(int i = 0; i < s.length; ++i) {
                        if (s[i]) {
                            if (result == "") {
                                result = it[i];
                            } else {
                                result = result + "\n" + it[i];
                            }
                        }
                    }

                    DialogBox.this.endDialog(result);
                }
            });
            builder.setNegativeButton(对话框类.CANCEL, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    DialogBox.this.endDialog("");
                }
            });
            builder.show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var6) {
                var6.printStackTrace();
            }

            return this.dialogResult;
        }
    }

    private static class MessageBox3 {
        private int dialogResult;
        private Handler mHandler;

        private MessageBox3() {
            this.dialogResult = 0;
        }

        public int getDialogResult() {
            return this.dialogResult;
        }

        public void setDialogResult(int dialogResult) {
            this.dialogResult = dialogResult;
        }

        public void endDialog(int result) {
            this.setDialogResult(result);
            Message m = this.mHandler.obtainMessage();
            this.mHandler.sendMessage(m);
        }

        public int showDialog(Builder builder, String title, String message1, String message2, String btnOK, String btnNO, boolean state, final BooleanReferenceParameter accept) {
            builder.setTitle(title).setCancelable(false);
            LinearLayout layout = new LinearLayout(mainActivity.getContext());
            layout.setOrientation(1);
            LayoutParams param = new LayoutParams(-1, -1);
            param.weight = 1.0F;
            TextView text = new TextView(mainActivity.getContext());
            text.setText(message1);
            CheckBox check = new CheckBox(mainActivity.getContext());
            check.setText(message2);
            check.setChecked(state);
            check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    accept.set(isChecked);
                }
            });
            layout.addView(text, param);
            layout.addView(check, param);
            builder.setView(layout);
            builder.setPositiveButton(btnOK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageBox3.this.endDialog(0);
                }
            });
            builder.setNegativeButton(btnNO, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageBox3.this.endDialog(1);
                }
            });
            builder.show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var15) {
                var15.printStackTrace();
            }

            return this.dialogResult;
        }
    }

    private static class MessageBox {
        private int dialogResult;
        private Handler mHandler;

        private MessageBox() {
            this.dialogResult = 0;
        }

        public int getDialogResult() {
            return this.dialogResult;
        }

        public void setDialogResult(int dialogResult) {
            this.dialogResult = dialogResult;
        }

        public void endDialog(int result) {
            this.setDialogResult(result);
            Message m = this.mHandler.obtainMessage();
            this.mHandler.sendMessage(m);
        }

        public int showDialog(Builder builder, String title, String message, String btnOK) {
            builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(btnOK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageBox.this.endDialog(0);
                }
            }).show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var6) {
                var6.printStackTrace();
            }

            return this.dialogResult;
        }

        public int showDialog2(Builder builder, String title, String message, String btnOK, String btnNO) {
            builder.setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(btnOK, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageBox.this.endDialog(0);
                }
            }).setNegativeButton(btnNO, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MessageBox.this.endDialog(1);
                }
            }).show();
            this.mHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message mesg) {
                    throw new RuntimeException();
                }
            };

            try {
                Looper.getMainLooper();
                Looper.loop();
            } catch (RuntimeException var7) {
                var7.printStackTrace();
            }

            return this.dialogResult;
        }
    }
}
