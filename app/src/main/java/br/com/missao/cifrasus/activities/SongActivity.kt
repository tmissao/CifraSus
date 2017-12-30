package br.com.missao.cifrasus.activities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.app.AppPreferences
import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.extensions.*
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredViewOperations
import br.com.missao.cifrasus.utils.PhraseSplitter
import br.com.missao.cifrasus.utils.PixelDimensions
import kotlinx.android.synthetic.main.activity_song.*
import kotlinx.android.synthetic.main.appbar_song.*
import javax.inject.Inject


/**
 * Songs Activity View
 */
class SongActivity : AppCompatActivity(), SongMvpRequiredViewOperations {

  /**
   * Presenter
   */
  @Inject lateinit var presenter: SongMvpPresenterOperations

  /**
   * Logger to report errors
   */
  @Inject lateinit var logger: Logger

  /**
   * Chord`s color
   */
  var chordColor: Int = 0

  /**
   * Song's data
   */
  var song: SongWrapper? = null

  /**
   * Song's font size defined by user
   */
  var contextFontSize = 0.0f

  /**
   * Indicates if layout was already measured
   */
  var isLayoutMeasured = false

  /**
   * Indicates is the FAB is rotate
   */
  var isFabRotated = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_song)
    inject()
    setupComponents()
    setupEvents()
  }

  /**
   * Injects activity dependencies
   */
  fun inject() {
    this.app.getDaggerViewComponent().inject(this)
    presenter.setView(this)
  }

  /**
   * Configures View to be shown
   */
  fun setupComponents() {
    contextFontSize = PixelDimensions.convertSpToPixels(resources.getDimension(R.dimen.fontsize_title), this.resources)
    chordColor = ContextCompat.getColor(this, R.color.chordTextColor)

    // It is necessary to cast appbarSong as Toolbar because the include tag on XML file. Since
    // include tag loads a view type
    this.setToolbar(appbarSong as Toolbar, showTitle = false, showHomeButton = false)
  }

  fun setupEvents() {
    addMeasureListener()

    /**
     * Hides FAB button on Scroll
     */
    scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
      override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY > oldScrollY && fabOptions.visibility == View.VISIBLE) {
          fabOptions.hide()
        } else if (scrollY <= oldScrollY && fabOptions.visibility != View.VISIBLE) {
          fabOptions.show()
        }
      }
    })

    fabOptions.setOnClickListener {
      toggleOptions()
    }

    frameBackground.setOnClickListener {
      toggleOptions()
    }

    fabFontSize.setOnClickListener {
      toggleOptions()
      buildTypographyDialog()
    }

    fabChangeTone.setOnClickListener {
      toggleOptions()
      buildChangeDialog()
    }
  }

  override fun onStart() {
    super.onStart()
    presenter.getSong(1)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.song_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return super.onOptionsItemSelected(item)
  }

  /**
   * Subscribes a layout listener
   */
  private fun addMeasureListener() {
    textViewModel.afterMeasured {
      isLayoutMeasured = true
      displaySongPhrases()
    }

    fabAddList.afterMeasured {
      hide()
    }

    fabChangeTone.afterMeasured {
      hide()
    }

    fabFontSize.afterMeasured {
      hide()
    }

    cardAddList.afterMeasured {
      showCardOptions(cardAddList, false)
    }

    cardChangeTone.afterMeasured {
      showCardOptions(cardChangeTone, false)
    }

    cardFontSize.afterMeasured {
      showCardOptions(cardFontSize, false)
    }

  }

  /**
   * Measures and displays Song's data without ellipsize its content
   */
  private fun displaySongPhrases() {
    val phrases = song?.phrases ?: listOf()

    if (phrases.isEmpty()) return
    val charSize = textViewModel.getCharacterPixelAverage()
    var measuredPhrases = arrayListOf<PhraseWrapper>()

    phrases.forEach { measuredPhrases.addAll(PhraseSplitter.measurePhrase(textViewModel, it)) }

    clearContainerData()

    measuredPhrases.forEach {
      val chordsLine = inflateChordLayout()

      if (it.chords.isNotEmpty()) {
        it.chords.forEach { chordsLine.addView(buildChordTextView(it, charSize)) }

      } else {
        // This empty chord line is add to keep the song's chords easy to read
        chordsLine.addView(buildChordTextView(buildEmptyChordWrapper(), charSize))
      }

      container.addView(chordsLine)
      container.addView(buildPhraseLayout(it))
    }
  }

  /**
   * Inflates the chord root layout
   */
  private fun inflateChordLayout(): FrameLayout {
    return container.inflate(R.layout.empty_frame, false) as FrameLayout
  }

  /**
   * Builds and configures a [TextView] to display a [chord]
   */
  private fun buildChordTextView(chord: ChordWrapper, charSize: Int): TextView {
    val textView = container.inflate(R.layout.partial_chord, false) as TextView
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, contextFontSize)
    textView.typeface = textViewModel.typeface
    textView.text = chord.value
    textView.setTextColor(this.chordColor)
    textView.setPadding(charSize * chord.placement, 0, 0, 0)
    return textView
  }

  /**
   * Builds and configures the layout to display [phrase]
   */
  private fun buildPhraseLayout(phrase: PhraseWrapper): View {
    val view = container.inflate(R.layout.partial_phrase, false)
    val textView = view.findViewById<TextView>(R.id.textPhrase)
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, contextFontSize)
    textView.typeface = textViewModel.typeface
    textView.text = phrase.text
    return view
  }

  /**
   *  Builds a Empty ChordWrapper
   */
  private fun buildEmptyChordWrapper(): ChordWrapper {
    return ChordWrapper("", 0)
  }

  /**
   * Removes previous container's data
   */
  private fun clearContainerData() {
    container.removeAllViews()
  }

  /**
   * Increases one SP in font size
   */
  private fun increaseFontSize() {
    textViewModel.setTextSize(TypedValue.COMPLEX_UNIT_SP, ++contextFontSize)
    displaySongPhrases()
  }

  /**
   * Decreases one SP in font size
   */
  private fun decreaseFontSize() {
    if (contextFontSize - 1 < 1) return

    textViewModel.setTextSize(TypedValue.COMPLEX_UNIT_SP, --contextFontSize)
    displaySongPhrases()
  }

  /**
   * Toggles FAB's state
   */
  private fun toggleOptions() {
    var degrees = -45f
    if (isFabRotated) {
      degrees *= -1
    }

    isFabRotated = !isFabRotated

    fabOptions.animate()
        .rotationBy(degrees)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .setDuration(AppPreferences.ANIMATION_FAST)
        .start()


    showActionOptions(isFabRotated)
    showBackgroundView(isFabRotated)
  }

  /**
   * Shows/Hides song action options
   */
  private fun showActionOptions(display: Boolean) {
    if (display) {
      fabAddList.show()
      fabFontSize.show()
      fabChangeTone.show()

    } else {
      fabAddList.hide()
      fabFontSize.hide()
      fabChangeTone.hide()
    }

    showCardOptions(cardAddList, display)
    showCardOptions(cardFontSize, display)
    showCardOptions(cardChangeTone, display)
  }

  private fun showCardOptions(card: CardView, display: Boolean) {

    if (display) {
      card.scaleX = 0f
      card.scaleY = 0f
      card.visibility = View.VISIBLE

      card.animate()
          .scaleYBy(1f)
          .scaleXBy(1f)
          .setInterpolator(OvershootInterpolator())
          .setDuration(AppPreferences.ANIMATION_FAST)
          .start()

    } else {
      card.animate()
          .scaleYBy(-1f)
          .scaleXBy(-1f)
          .withEndAction { frameBackground.visibility = View.GONE }
          .setInterpolator(AccelerateDecelerateInterpolator())
          .setDuration(AppPreferences.ANIMATION_FAST)
          .start()
    }
  }

  /**
   * Shows/Hides background options view
   */
  private fun showBackgroundView(display: Boolean) {

    if (display) {
      frameBackground.alpha = 0f
      frameBackground.visibility = View.VISIBLE

      frameBackground.animate()
          .alphaBy(1f)
          .setInterpolator(AccelerateDecelerateInterpolator())
          .setDuration(AppPreferences.ANIMATION_FAST)
          .start()

    } else {
      frameBackground.animate()
          .alphaBy(-1f)
          .withEndAction { frameBackground.visibility = View.GONE }
          .setInterpolator(AccelerateDecelerateInterpolator())
          .setDuration(AppPreferences.ANIMATION_FAST)
          .start()
    }
  }

  /**
   * Builds Material Dialog to adjust typographic
   */
  private fun buildTypographyDialog() {
    val inflater = this.layoutInflater
    var seekFontSize: SeekBar? = null
    var textFontSize: TextView? = null

    val dialogView = inflater.inflate(R.layout.dialog_font_size, null).apply {
      seekFontSize = findViewById<SeekBar>(R.id.seekFontSize)
      textFontSize = findViewById<TextView>(R.id.textLyrics)
    }
    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle).apply {
      this.setView(dialogView)
      this.setTitle(getString(R.string.select_font_size))
      this.setPositiveButton(getString(R.string.positive_button_text), null)
    }

    seekFontSize?.progress = contextFontSize.toInt()
    textFontSize?.text = song?.phrases?.first()?.text

    seekFontSize?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onStartTrackingTouch(seekBar: SeekBar) {}
      override fun onStopTrackingTouch(seekBar: SeekBar) {}
      override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        contextFontSize = if (progress > 0) progress.toFloat() else 1.toFloat()
        textFontSize?.setTextSize(TypedValue.COMPLEX_UNIT_SP, contextFontSize)
        textViewModel.setTextSize(TypedValue.COMPLEX_UNIT_SP, contextFontSize)
        displaySongPhrases()
      }
    })

    builder.show()
  }

  /**
   * Builds Material Dialog to adjust music tone
   */
  private fun buildChangeDialog() {
    val inflater = this.layoutInflater
    var seekToneDegree: SeekBar? = null
    var textToneFrom: TextView? = null
    var textToneTo: TextView? = null
    var textToneDifference: TextView? = null

    val dialogView = inflater.inflate(R.layout.dialog_change_tone, null).apply {
      seekToneDegree = findViewById<SeekBar>(R.id.seekToneDegree)
      textToneFrom = findViewById<TextView>(R.id.textToneFrom)
      textToneTo = findViewById<TextView>(R.id.textToneTo)
      textToneDifference = findViewById<TextView>(R.id.textToneDifference)
    }


    val middle = (seekToneDegree?.max ?: 0) / 2
    var difference = 0
    seekToneDegree?.progress = middle
    textToneFrom?.text = song?.tone?.value
    textToneTo?.text = song?.tone?.value
    textToneDifference?.text = resources.getQuantityString(R.plurals.tones, 0, 0.toString())

    seekToneDegree?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onStartTrackingTouch(seekBar: SeekBar) {}
      override fun onStopTrackingTouch(seekBar: SeekBar) {}
      override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        difference = progress - middle
        val displayDifference = (difference.toFloat() / 2)
        val normalized = (Math.abs(displayDifference) + 0.5).toInt()
        textToneDifference?.text = resources.getQuantityString(R.plurals.tones, normalized, displayDifference.toString())
        song?.let { textToneTo?.text = Chord.change(it.tone, difference).value }
      }
    })

    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle).apply {
      this.setView(dialogView)
      this.setTitle(getString(R.string.select_tone))
      this.setPositiveButton(getString(R.string.positive_button_text)) { dialogInterface, i ->
        song?.let { presenter.changeTone(it, difference) }
      }
      this.setNegativeButton(getString(R.string.negative_button_text), null)
    }

    builder.show()
  }

  /**
   * Obtains song's data
   */
  override fun onGetSong(music: SongWrapper) {
    song = music
    textSong.text = music.name
    textArtist.text = music.artist
    if (isLayoutMeasured) {
      displaySongPhrases()
    }
  }

  /**
   * Obtains song's changed tone data
   */
  override fun onChangeTone(song: SongWrapper) {
    onGetSong(song)
  }
}
