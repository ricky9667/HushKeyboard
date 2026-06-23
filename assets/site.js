(function () {
  var root = document.documentElement;
  var toggle = document.getElementById('theme-toggle');
  var icon = document.getElementById('theme-icon');
  var themeColor = document.querySelector('meta[name="theme-color"]');
  var media = window.matchMedia ? window.matchMedia('(prefers-color-scheme: dark)') : null;
  var stored;
  var darkModeIcon = '<path fill="currentColor" d="M12 21q-3.75 0-6.375-2.625T3 12t2.625-6.375T12 3q.35 0 .688.025t.662.075q-1.025.725-1.638 1.888T11.1 7.5q0 2.25 1.575 3.825T16.5 12.9q1.375 0 2.525-.613T20.9 10.65q.05.325.075.662T21 12q0 3.75-2.625 6.375T12 21" />';
  var lightModeIcon = '<path fill="currentColor" d="M8.463 15.538Q7 14.075 7 12t1.463-3.537T12 7t3.538 1.463T17 12t-1.463 3.538T12 17t-3.537-1.463M5 13H1v-2h4zm18 0h-4v-2h4zM11 5V1h2v4zm0 18v-4h2v4zM6.4 7.75L3.875 5.325L5.3 3.85l2.4 2.5zm12.3 12.4l-2.425-2.525L17.6 16.25l2.525 2.425zM16.25 6.4l2.425-2.525L20.15 5.3l-2.5 2.4zM3.85 18.7l2.525-2.425L7.75 17.6l-2.425 2.525z" />';

  try { stored = localStorage.getItem('hush-theme'); } catch (_) { }

  function applyTheme(theme) {
    var dark = theme === 'dark';
    root.dataset.theme = theme;

    if (toggle) {
      toggle.setAttribute('aria-pressed', String(dark));
      toggle.setAttribute('aria-label', dark ? 'Switch to light theme' : 'Switch to dark theme');
    }

    if (icon) {
      icon.innerHTML = dark ? lightModeIcon : darkModeIcon;
    }

    if (themeColor) {
      themeColor.setAttribute('content', dark ? '#000000' : '#ffffff');
    }
  }

  applyTheme(stored === 'light' || stored === 'dark' ? stored : (media && media.matches) ? 'dark' : 'light');

  if (toggle) {
    toggle.addEventListener('click', function () {
      var next = root.dataset.theme === 'dark' ? 'light' : 'dark';
      applyTheme(next);
      try { localStorage.setItem('hush-theme', next); } catch (_) { }
    });
  }

  if (media) {
    let mediaListener = function (event) {
      try {
        if (localStorage.getItem('hush-theme')) return;
      } catch (_) { }
      applyTheme(event.matches ? 'dark' : 'light');
    };

    if (media.addEventListener) {
      media.addEventListener('change', mediaListener);
    } else if (media.addListener) {
      media.addListener(mediaListener);
    }
  }

  var year = document.getElementById('year');
  if (year) {
    year.textContent = new Date().getFullYear();
  }
})();
