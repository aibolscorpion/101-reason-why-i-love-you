package kz.edu.sdu.forasel;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

public class MainActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {
    final int MAX_STREAMS = 5;
    final String LOG_TAG = "myLogs";
    SoundPool sp;
    int soundIdShot;
    Context context;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mediaPlayer = MediaPlayer.create(this, R.raw.mahabbatim);
        mediaPlayer.start();

        ImageButton button = (ImageButton) findViewById(R.id.zhurek);
        Animation pulse = AnimationUtils.loadAnimation(context, R.anim.breaking_heart);
        button.startAnimation(pulse);

        // logMemory();
        readImage();
        // logMemory();

    }

    private void readImage() {
        ImageView zhanim = (ImageView) findViewById(R.id.zhanim);
        Bitmap bitmap = decodeSampledBitmapFromResource(R.drawable.marta, 400, 400);
        Log.d("log", String.format("bitmap size = %sx%s, byteCount = %s",
                bitmap.getWidth(), bitmap.getHeight(),
                (int) (bitmap.getByteCount() / 1024)));
        zhanim.setImageBitmap(bitmap);
    }

    private void logMemory() {
        Log.i("log", String.format("Total memory = %s",
                (int) (Runtime.getRuntime().totalMemory() / 1024)));
    }

    public Bitmap decodeSampledBitmapFromResource(int id,
                                                  int reqWidth, int reqHeight) {

        // Читаем с inJustDecodeBounds=true для определения размеров
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getApplicationContext().getResources(), id, options);
        // Вычисляем inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Читаем с использованием inSampleSize коэффициента
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getApplicationContext().getResources(), id, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Реальные размеры изображения
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Вычисляем наибольший inSampleSize, который будет кратным двум
            // и оставит полученные размеры больше, чем требуемые
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void heart(View v) {
        String str = sozder();
        int id = suretter();
        showNotifications(str,id);
    }

    public void showNotifications(String str,int id) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text2);
        text.setText(sozder());

        ImageView notifImage = (ImageView) layout.findViewById(R.id.notifImage);
        Bitmap bitmap = decodeSampledBitmapFromResource(id, 200, 200);

        notifImage.setImageBitmap(bitmap);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    public String sozder() {
        Random r = new Random();
        int rand = r.nextInt(101);
        Log.i("aibol", rand + "");
        return tilek(rand);
    }

    public int suretter() {
        Random r = new Random();
        int rand = r.nextInt(16);
        return asel(rand);
    }

    public int asel(int number) {
        int zhan=0;
        switch (number) {
            case 0:
                zhan = R.drawable.a;
                break;
            case 1:
                zhan = R.drawable.b;
                break;
            case 2:
                zhan = R.drawable.c;
                break;
            case 3:
                zhan = R.drawable.d;
                break;
            case 4:
                zhan = R.drawable.e;
                break;
            case 5:
                zhan = R.drawable.r;
                break;
            case 6:
                zhan = R.drawable.g;
                break;
            case 7:
                zhan = R.drawable.h;
                break;
            case 8:
                zhan = R.drawable.e;
                break;
            case 9:
                zhan = R.drawable.j;
                break;
            case 10:
                zhan = R.drawable.k;
                break;
            case 11:
                zhan = R.drawable.h;
                break;
            case 12:
                zhan = R.drawable.l;
                break;
            case 13:
                zhan = R.drawable.m;
                break;
            case 14:
                zhan = R.drawable.n;
                break;
            case 15:
                zhan = R.drawable.o;
                break;
            case 16:
                zhan = R.drawable.p;
                break;
            case 17:
                zhan = R.drawable.q;
                break;

        }
        return zhan;
    }
    public String tilek(int number){

        String text="";
        switch(number){
            case 0:
                text = "Сен тек мендіксің";
                break;
            case 1:
                text = "Сенімен бірге болу мен үшін бақыт";
                break;
            case 2:
                text = "Сенімен ешқашан ішпысарлық емес";
                break;
            case 3:
                text = "Сен мені қалай күлдіруді білесің";
                break;
            case 4:
                text = "Сен әрдайым менің көңіл-күйімді сезесің";
                break;
            case 5:
                text = "Сен мені менің кемшіліктеріммен жақсы көресің";
                break;
            case 6:
                text = "Мен сені құшақтағанда бәрі жақсы";
                break;
            case 7:
                text = "Менің өмірім тек саған ғана арналғанын қалаймын";
                break;
            case 8:
                text = "Біздің армандарымыз жиі ұқсасады";
                break;
            case 9:
                text = "Сен менің ең жақын досымсың";
                break;
            case 10:
                text = "Сенің әр қылығын маған ұнайды";
                break;
            case 11:
                text = "Сенің даусыңды естіген маған ұнайды";
                break;
            case 12:
                text = "Сен жақсы көруді үйреттің";
                break;
            case 13:
                text = "Сенің көздеріңе қарап, арманымның орындалғанын түсіндім";
                break;
            case 14:
                text = "Сен әрдайым менің ойымдасың";
                break;
            case 15:
                text = "Сен әрдайым нәзіксің";
                break;
            case 16:
                text = "Сені құшағымнан жібергім келмейді";
                break;
            case 17:
                text = "Сенің кішкене қылығың мен үшін маңызды";
                break;
            case 18:
                text = "Сенімен барлық жерде өзімді жақсы сезінем";
            case 19:
                text = "Сенімен барлық жерде өзімді жақсы сезінем";
                break;
            case 20:
                text = "Сен магнит сияқтысың, саған әрқашан тартып тұрады";
                break;
            case 21:
                text = "Сенің ,бақытты болғаның мен үшін маңызды";
                break;
            case 22:
                text = "Сен менің ең ғажайып түсімсің";
                break;
            case 23:
                text = "Сенің жаныңда болғанда ләззат аламын";
                break;
            case 24:
                text = "Сенің кеңестерің көмектеседі";
                break;
            case 25:
                text = "Сен менің сырларымды білесің, және ешкімге тіс жармайсың";
                break;
            case 26:
                text = "Мен саған ақымақ сұрақтарды қоя аламын";
                break;
            case 27:
                text = "Сен менің күнімсің";
                break;
            case 28:
                text = "Екеуміз бірге жақсы жұппыз";
                break;
            case 29:
                text = "Сен мен үшін қалаған ісіңнен бас тартуға дайынсың";
                break;
            case 30:
                text = "Сен маған тамақ істеп бергің келеді";
                break;
            case 31:
                text = "Сен маған тамақ істеп бергің келеді";
                break;
            case 32:
                text = "Сен менің өмірімде пайда болғаннан бері бәрі керемет бола бастады";
                break;
            case 33:
                text = "Сенің махаббатың мені сақтайды";
                break;
            case 34:
                text = "Саған менің өмірімнің әр ұсақ түйегі қызық";
                break;
            case 35:
                text = "Сенімен бірге болғанда уақыт зымырайды";
                break;
            case 36:
                text = "Кей кездері сені қатты құшақтағым келеді";
                break;
            case 37:
                text = "Менің жүрегімнің барлық кілті сенде";
                break;
            case 38:
                text = "Менің ең бағалы байлығымсын";
                break;
            case 39:
                text = "Сен менің өмірімді бақытты қылып жатсың";
                break;
            case 40:
                text = "Мен сенімен бірге мәңгі болғым келеді";
                break;
            case 41:
                text = "Саған қараған сәтте жүрегім тоқтайды";
                break;
            case 42:
                text = "Сені көрген сәтте көңілім көтеріледі";
                break;
            case 43:
                text = "Сенің құшағың әрдайым жылы";
                break;
            case 44:
                text = "Сен әрдайым ғажайып көрінесің";
                break;
            case 45:
                text = "Сен маған сенесің";
                break;
            case 46:
                text = "Менің жүрегім сен үшін соғады";
                break;
            case 47:
                text = "Сенімен бірге болған сәттер бақытқа болы";
                break;
            case 48:
                text = "Сені ойлаған сәтте жүрегім махаббатқа толады";
                break;
            case 49:
                text = "Мен саған сене аламын";
                break;
            case 50:
                text = "Сенің бір күлкіңнің өзі менің көңіл күйімді көтереді";
                break;
            case 51:
                text = "Мен семен бірге болған сәтте,басқа нәрселер маңызды емес";
                break;
            case 52:
                text = "Сен әрдайым менің жағымдасың";
                break;
            case 53:
                text = "Сен маған қалай бақыт сыйлауды білесің";
                break;
            case 54:
                text = "Біз қалаған әңгімені айта аламыз";
                break;
            case 55:
                text = "Сен тәрбиелі қызсың";
                break;
            case 56:
                text = "Мен үшін сенен асқан қыз жоқ";
                break;
            case 57:
                text = "Сенің көздеріңнен маған деген махаббатты көремін";
                break;
            case 58:
                text = "Сен мен айтқанда әрдайым тыңдайсың";
                break;
            case 59:
                text = "Сен әлемдегі ғажайып жансың";
                break;
            case 60:
                text = "Сен мені сағынасың";
                break;
            case 61:
                text = "Саған шашлык унайды";
                break;
            case 62:
                text = "Саған сок унайды";
                break;
            case 63:
                text = "Маған қолыма духиіңді сеуіп бердің";
                break;
            case 64:
                text = "Маған шай құйып бердің";
                break;
            case 65:
                text = "Сен ақылдысың";
                break;
            case 66:
                text = "Сен мемен ашық сөйлесесің";
                break;
            case 67:
                text = "Мен саған ұзақ уақыт ренжи алмайм";
                break;
            case 68:
                text = "Саған массаж жасаған ұнайды";
                break;
            case 69:
                text = "Сенің зиянды әдеттерің жоқ";
                break;
            case 70:
                text = "Сен менің түстеріме кіресің";
                break;
            case 71:
                text = "Сен мені бағалайсың";
                break;
            case 72:
                text = "Сен менің өмірімсің";
                break;
            case 73:
                text = "Сені ешқандай қыз алмастырмайды";
                break;
            case 74:
                text = "Семен бірге серуендегенді жақсы көремін";
                break;
            case 75:
                text = "Біз бір біріміз үшін жаралғанбыз";
                break;
            case 76:
                text = "Маған түнгі телефонмен сөйлескеніміз ұнайды";
                break;
            case 77:
                text = "Сен мені басқа жігіттердің ішінен таңдағаның үшін";
                break;
            case 78:
                text = "Сенің маған қарағаның ұнайды";
                break;
            case 79:
                text = "Маған екеуіздің құшақтасқанымыз ұнайды";
                break;
            case 80:
                text = "Сенімен уақыт өткізген ұнайды";
                break;
            case 81:
                text = "Сен менің көңіл күйімді көтеруді білесің";
                break;
            case 82:
                text = "Сен маған тамақты аз же дейсің";
                break;
            case 83:
                text = "Сенің арманың бақытты ана және әйел болу";
                break;
            case 84:
                text = "Маған сенің духиіңнің иістері ұнайды";
                break;
            case 85:
                text = "Сенің өлең айтқаның ұнайды";
                break;
            case 86:
                text = "Сен менің махаббатымсың";
                break;
            case 87:
                text = "Мен сені жиі сағынамын";
                break;
            case 88:
                text = "Сенсіз болашағымды елестете алмаймын";
                break;
            case 89:
                text = "Кейде сенің мемен ұрысқың келмейді";
                break;
            case 90:
                text = "Менің ойымның 100% тек сен туралы";
                break;
            case 91:
                text = "Сені анама ұқсатам";
                break;
            case 92:
                text = "Сенімен үндемей тұра аламын";
                break;
            case 93:
                text = "Сенің менің түстеріме кіргенің ұнайды";
                break;
            case 94:
                text = "Сені тыңдаған маған ұнайды";
                break;
            case 95:
                text = "Сен менің бөлігімсің";
                break;
            case 96:
                text = "Сенен басқа ешкім керек қылмаймын";
                break;
            case 97:
                text = "Сенімен бірге болған сәтте бақыттың не екенін сезінемін";
                break;
            case 98:
                text = "Сенің су шашыңмен сары футболкаң ұнайды";
                break;
            case 99:
                text = "Сен ұқыпты адамсың";
                break;
            case 100:
                text = "Сен жақсы адамсың";
                break;
            case 101:
                text = "Сенің ерніңнен асқан тәтті нәрсе жоқ";
                break;
        }
        return text;
    }
}